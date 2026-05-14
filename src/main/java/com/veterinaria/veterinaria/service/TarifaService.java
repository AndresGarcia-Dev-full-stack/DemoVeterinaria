package com.veterinaria.veterinaria.service;

import com.veterinaria.veterinaria.model.ServicioVeterinario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarifaService {

    private final List<ServicioVeterinario> servicios =
            new ArrayList<>();

    private Long consecutivo = 1L;

    public void guardar(ServicioVeterinario servicio) {

        servicio.setId(consecutivo++);

        servicios.add(servicio);
    }

    public List<ServicioVeterinario> obtenerTodos() {

        return servicios;
    }
}