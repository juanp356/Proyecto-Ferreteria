package com.example.ferreteria.Model;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Purchase_orders")
public class Purchase_orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "date_register")
    private LocalDateTime fechaRegistro;

    private String estado;           // PENDIENTE, PAGADA, ENVIADA, etc.
    private String observaciones;    // Notas adicionales
    private LocalDateTime fechaEntrega; // Fecha estimada de entrega

    public Purchase_orders(Long id, Client client, BigDecimal total, LocalDateTime fechaRegistro, String estado, String observaciones, LocalDateTime fechaEntrega) {
        this.id = id;
        this.client = client;
        this.total = total;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.observaciones = observaciones;
        this.fechaEntrega = fechaEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    // ====== MÉTODOS DE NEGOCIO ======

    /**
     * Verifica si la orden pertenece al cliente especificado
     */
    public boolean perteneceAlClient(Long clientId) {
        return this.client != null && this.client.getClient_id().equals(clientId);
    }

    /**
     * Verifica si la orden fue creada hoy
     */
    public boolean esOrdenDeHoy() {
        return this.fechaRegistro.toLocalDate().equals(LocalDateTime.now().toLocalDate());
    }

    /**
     * Actualiza el estado de la orden
     */
    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    /**
     * Verifica si la orden está pagada
     */
    public boolean estaPagada() {
        return "PAGADA".equalsIgnoreCase(this.estado);
    }

    @Override
    public String toString() {
        return "Purchase_orders{" +
                "id=" + id +
                ", clientId=" + (client != null ? client.getClient_id() : null) +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}

