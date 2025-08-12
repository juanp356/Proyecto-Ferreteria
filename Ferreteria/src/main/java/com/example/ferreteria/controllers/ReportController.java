package com.example.ferreteria.controllers;

import com.example.ferreteria.Model.Client;
import com.example.ferreteria.Model.Proceeds;
import com.example.ferreteria.Model.Vending;
import com.example.ferreteria.Repository.ClientRepository;
import com.example.ferreteria.Repository.ProceedsRepository;
import com.example.ferreteria.Repository.VendingRepository;
import com.example.ferreteria.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class ReportController {

    private final VendingRepository vendingRepository;
    private final ProceedsRepository proceedsRepository;
    private final ClientRepository clientRepository;
    private final ReportService reportService;

    public ReportController(VendingRepository vendingRepository, ProceedsRepository proceedsRepository, ClientRepository clientRepository, ReportService reportService) {
        this.vendingRepository = vendingRepository;
        this.proceedsRepository= proceedsRepository;
        this.clientRepository = clientRepository ;
        this.reportService = reportService;
    }

    // 📌 Reporte Ventas
    @GetMapping("/sales/diary")
    public ResponseEntity<byte[]> reporteVentasDiarias() throws Exception {
        List<Vending> ventas = vendingRepository.findByDate(LocalDate.now());
        Map<String, Object> params = Map.of("titulo", "Reporte de Ventas Diarias");
        return buildPDF("ventas", params, ventas, "ventas_diarias.pdf");
    }

//    @GetMapping("/sales/weekly")
//    public ResponseEntity<byte[]> reporteVentasSemanales() throws Exception {
//        LocalDate fin = LocalDate.now();
//        LocalDate inicio = fin.minusDays(7);
//        List<Vending> ventas = vendingRepository.findBetweenDate(inicio, fin);
//        Map<String, Object> params = Map.of("titulo", "Reporte de Ventas Semanales");
//        return buildPDF("ventas", params, ventas, "ventas_semanales.pdf");
//    }

//    @GetMapping("/sales/monthly")
//    public ResponseEntity<byte[]> reporteVentasMensuales() throws Exception {
//        LocalDate fin = LocalDate.now();
//        LocalDate inicio = fin.withDayOfMonth(1);
//        List<Vending> ventas = vendingRepository.findBetweenFechas(inicio, fin);
//        Map<String, Object> params = Map.of("titulo", "Reporte de Ventas Mensuales");
//        return buildPDF("ventas", params, ventas, "ventas_mensuales.pdf");
//    }

//    // 📌 Reporte Productos
//    @GetMapping("/products/best-sellers")
//    public ResponseEntity<byte[]> reporteProductosMasVendidos() throws Exception {
//        List<Proceeds> productos = proceedsRepository.findMasVendidos();
//        Map<String, Object> params = Map.of("titulo", "Productos Más Vendidos");
//        return buildPDF("productos", params, productos, "productos_mas_vendidos.pdf");
//    }

    @GetMapping("/products/low-stock")
    public ResponseEntity<byte[]> reporteProductosStockBajo() throws Exception {
        List<Proceeds> productos = proceedsRepository.findStockBajo();
        Map<String, Object> params = Map.of("titulo", "Productos con Stock Bajo");
        return buildPDF("productos", params, productos, "productos_stock_bajo.pdf");
    }

//    // 📌 Reporte Clientes
//    @GetMapping("/clients/frequent")
//    public ResponseEntity<byte[]> reporteClientesFrecuentes() throws Exception {
//        List<Client> clientes = clientRepository.findClientesFrecuentes();
//        Map<String, Object> params = Map.of("titulo", "Clientes Frecuentes");
//        return buildPDF("clientes", params, clientes, "clientes_frecuentes.pdf");
//    }

    // Método genérico para generar PDF
    private ResponseEntity<byte[]> buildPDF(String plantilla, Map<String, Object> params, List<?> datos, String nombreArchivo) throws Exception {
        byte[] pdf = reportService.exportarPDF(plantilla, params, datos);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
