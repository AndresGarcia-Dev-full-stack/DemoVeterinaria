package com.veterinaria.veterinaria.model;

public class ItemFactura {

    private String servicio;

    private int cantidad;

    private double valor;

    public double getSubtotal() {
        return cantidad * valor;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}