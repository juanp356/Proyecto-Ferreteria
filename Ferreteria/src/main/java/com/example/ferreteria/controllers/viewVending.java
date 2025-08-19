package com.example.ferreteria.controllers;

import com.example.ferreteria.Model.Client;
import com.example.ferreteria.Model.Vending;
import com.example.ferreteria.Repository.ClientRepository;
import com.example.ferreteria.Repository.EmployeeRepository;
import com.example.ferreteria.Repository.ProceedsRepository;
import com.example.ferreteria.Repository.VendingRepository;
import com.example.ferreteria.service.CartItem;
import com.example.ferreteria.service.ProceedsService;
import com.example.ferreteria.service.VendingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/vista/vending")
@SessionAttributes("cart")
public class viewVending
{

    private final ProceedsRepository proceedsRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final VendingRepository vendingRepository;
    private final VendingService vendingService;

    // Formatter para peso colombiano SIN decimales (ya que manejas centavos)
    public static final NumberFormat COP_FORMATTER = NumberFormat.getInstance(new Locale("es", "CO"));

    static {
        COP_FORMATTER.setMaximumFractionDigits(0);
        COP_FORMATTER.setMinimumFractionDigits(0);
    }

    public viewVending(ProceedsRepository p, ClientRepository c, EmployeeRepository e, VendingRepository v, VendingService s){
        this.proceedsRepository=p; this.clientRepository=c; this.employeeRepository=e; this.vendingRepository=v; this.vendingService=s;
    }

    @ModelAttribute("cart")
    public List<CartItem> cart(){ return new ArrayList<>(); }

    @GetMapping
    public String pos(Model model, @ModelAttribute("cart") List<CartItem> cart){
        model.addAttribute("products", proceedsRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());

        int total = cart.stream().mapToInt(CartItem::getSubtotalCents).sum();

        // Formatear correctamente el total
        String totalFormatted = "$" + COP_FORMATTER.format(total);

        model.addAttribute("cart", cart);
        model.addAttribute("cartTotal", total);        // Número para JavaScript
        model.addAttribute("cartTotalFormatted", totalFormatted);  // String formateado para mostrar

        return "sales/pos";
    }

    // Agregar método helper para formatear en Thymeleaf
    @ModelAttribute("formatCOP")
    public java.util.function.Function<Integer, String> formatCOP() {
        return (Integer price) -> "$" + COP_FORMATTER.format(price);
    }

    @PostMapping("/add/{productId}")
    public String add(@PathVariable Long productId, @ModelAttribute("cart") List<CartItem> cart){
        var p = proceedsRepository.findById(productId).orElseThrow();
        var existing = cart.stream().filter(i-> i.proceedsId.equals(productId)).findFirst().orElse(null);
        if(existing==null){
            CartItem it=new CartItem();
            it.proceedsId=p.getProceeds_id(); it.name=p.getName();
            it.unitPriceCents= p.getPrice();
            it.qty=1;
            cart.add(it);
        }else{
            if (p.getQuantity() > existing.qty) existing.qty++;
        }
        return "redirect:/vista/vending";
    }

    @PostMapping("/inc/{productId}")
    public String inc(@PathVariable Long productId, @ModelAttribute("cart") List<CartItem> cart){
        var p = proceedsRepository.findById(productId).orElseThrow();
        cart.stream().filter(i->i.proceedsId.equals(productId)).findFirst().ifPresent(i -> {
            if (p.getQuantity() > i.qty) i.qty++;
        });
        return "redirect:/vista/vending";
    }

    @PostMapping("/dec/{productId}")
    public String dec(@PathVariable Long productId, @ModelAttribute("cart") List<CartItem> cart){
        cart.removeIf(i -> {
            if(i.proceedsId.equals(productId)){
                i.qty--; return i.qty<=0;
            }
            return false;
        });
        return "redirect:/vista/vending";
    }

    @PostMapping("/remove/{productId}")
    public String remove(@PathVariable Long productId, @ModelAttribute("cart") List<CartItem> cart){
        cart.removeIf(i-> i.proceedsId.equals(productId));
        return "redirect:/vista/vending";
    }

    @PostMapping("/cancel")
    public String cancel(SessionStatus status){
        status.setComplete(); // limpia carrito (cancelación antes de confirmar)
        return "redirect:/vista/vending";
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam(required=false) String clientId,
                                     @RequestParam(required=false) String employeeId,
                                     @RequestParam int paidCents,
                                     @ModelAttribute("cart") List<CartItem> cart,
                                     SessionStatus status) {

        try {
            System.out.println("=== DEBUG VENTA ===");
            System.out.println("clientId recibido: '" + clientId + "'");
            System.out.println("employeeId recibido: '" + employeeId + "'");

            // Validar que hay productos en el carrito
            if (cart == null || cart.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El carrito está vacío");
                return ResponseEntity.badRequest().body(error);
            }

            // Validar empleado - OBLIGATORIO
            if (employeeId == null || employeeId.trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Debe seleccionar un empleado");
                return ResponseEntity.badRequest().body(error);
            }

            Long finalEmployeeId;
            try {
                finalEmployeeId = Long.parseLong(employeeId.trim());
            } catch (NumberFormatException e) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "ID de empleado inválido: " + employeeId);
                return ResponseEntity.badRequest().body(error);
            }

            // Validar que el empleado existe
            if (!employeeRepository.existsById(finalEmployeeId)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El empleado seleccionado no existe");
                return ResponseEntity.badRequest().body(error);
            }

            // Manejar cliente - Si es vacío o null, usar/crear "Consumidor Final"
            Long finalClientId;
            if (clientId == null || clientId.trim().isEmpty()) {
                // Buscar cliente "Consumidor Final" o crear uno
                var consumidorFinal = clientRepository.findAll().stream()
                        .filter(c -> "Consumidor Final".equalsIgnoreCase(c.getName()) ||
                                "Consumidor final".equalsIgnoreCase(c.getName()))
                        .findFirst()
                        .orElse(null);

                if (consumidorFinal == null) {
                    // Crear cliente "Consumidor Final"
                    Client newClient = new Client();
                    newClient.setName("Consumidor Final");
                    newClient.setPhone("000-000-0000");
                    newClient.setEmail("consumidor@final.com");
                    newClient.setAddress("N/A");
                    consumidorFinal = clientRepository.save(newClient);
                    System.out.println("Cliente 'Consumidor Final' creado con ID: " + consumidorFinal.getClient_id());
                }

                finalClientId = consumidorFinal.getClient_id();
            } else {
                // Convertir ID del cliente seleccionado
                try {
                    finalClientId = Long.parseLong(clientId.trim());
                } catch (NumberFormatException e) {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "ID de cliente inválido: " + clientId);
                    return ResponseEntity.badRequest().body(error);
                }

                // Validar que el cliente existe
                if (!clientRepository.existsById(finalClientId)) {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "El cliente seleccionado no existe");
                    return ResponseEntity.badRequest().body(error);
                }
            }

            // Validar monto pagado
            int cartTotal = cart.stream().mapToInt(CartItem::getSubtotalCents).sum();
            if (paidCents < cartTotal) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El monto pagado no puede ser menor al total de la venta");
                return ResponseEntity.badRequest().body(error);
            }

            System.out.println("finalClientId: " + finalClientId);
            System.out.println("finalEmployeeId: " + finalEmployeeId);
            System.out.println("paidCents: " + paidCents);
            System.out.println("cartTotal: " + cartTotal);
            System.out.println("=== FIN DEBUG ===");

            // Realizar la venta
            var venta = vendingService.confirmSale(finalClientId, finalEmployeeId, cart, paidCents);
            status.setComplete(); // Limpiar carrito

            // Respuesta exitosa
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Venta realizada exitosamente");
            response.put("ventaId", venta.getId());
            response.put("total", cartTotal);
            response.put("cambio", paidCents - cartTotal);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("Error al procesar venta: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put("error", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/lista")
    public String list(Model model){
        model.addAttribute("sales", vendingRepository.findAll());
        return "sales/list";
    }

    @GetMapping("/detalle/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("sale", vendingRepository.findById(id).orElseThrow());
        return "sales/modal :: content";
    }

    @PostMapping("/anular/{id}")
    public String cancelConfirmed(@PathVariable Long id){
        vendingService.cancelConfirmedSale(id);
        return "redirect:/vista/vending/lista";
    }

    @GetMapping("/vista/vending/detalle/{id}")
    public String getSaleDetail(@PathVariable Long id, Model model) {
        try {
            System.out.println("=== INICIANDO getSaleDetail para ID: " + id + " ===");

            // Datos de prueba hardcodeados
            Map<String, Object> sale = new HashMap<>();
            sale.put("id", id);
            sale.put("vendingDate", LocalDateTime.now());
            sale.put("totalAmount", 50000);
            sale.put("client", null);
            sale.put("employee", null);
            sale.put("status", null);
            sale.put("items", null);
            sale.put("paidAmount", null);

            model.addAttribute("sale", sale);

            System.out.println("=== Datos agregados al modelo correctamente ===");
            System.out.println("=== Intentando retornar fragmento ===");

            return "fragments/sale-detail";

        } catch (Exception e) {
            System.err.println("=== ERROR EN EL CONTROLADOR ===");
            e.printStackTrace();
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}

