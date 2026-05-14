package com.veterinaria.modelo;

public class HistorialClinico {

    private Long id;
    private Long mascotaId;

    private String tipo;
    private String descripcion;
    private String fecha;

    public HistorialClinico() {
    }

    public HistorialClinico(Long id, Long mascotaId, String tipo,
                            String descripcion, String fecha) {

        this.id = id;
        this.mascotaId = mascotaId;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
