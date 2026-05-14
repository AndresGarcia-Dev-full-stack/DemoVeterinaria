package com.veterinaria.veterinaria.model;

import java.util.ArrayList;
import java.util.List;

public class Factura {

    private Long id;

    private String cliente;

    private String mascota;

    private String documentoCliente;

    public List<ItemFactura> items = new ArrayList<>();

    private double total;

    public void calcularTotal() {

        total = items.stream()
                .mapToDouble(ItemFactura::getSubtotal)
                .sum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

}