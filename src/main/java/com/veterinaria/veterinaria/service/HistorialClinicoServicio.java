package com.veterinaria.veterinaria.service;

import com.veterinaria.modelo.HistorialClinico;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialClinicoServicio {

    private List<HistorialClinico> historial = new ArrayList<>();

    public void guardar(HistorialClinico h) {
        historial.add(h);
    }

    public List<HistorialClinico> obtenerPorMascota(Long mascotaId) {

        List<HistorialClinico> lista = new ArrayList<>();

        for (HistorialClinico h : historial) {

            if (h.getMascotaId().equals(mascotaId)) {
                lista.add(h);
            }
        }

        return lista;
    }
    public List<HistorialClinico> obtenerTodos() {

        return historial;
    }
}