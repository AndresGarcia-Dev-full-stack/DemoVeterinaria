package com.veterinaria.veterinaria.service;

import com.veterinaria.veterinaria.model.Cita;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitaService {

    private final List<Cita> citas =
            new ArrayList<>();

    public void guardar(Cita cita) {

        citas.add(cita);
    }

    public List<Cita> obtenerTodas() {

        return citas;
    }
}