package com.veterinaria.veterinaria;

import com.veterinaria.modelo.HistorialClinico;
import com.veterinaria.veterinaria.model.Mascota;

import com.veterinaria.veterinaria.service.HistorialClinicoServicio;
import com.veterinaria.veterinaria.service.MascotaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HistorialClinicoController {

    @Autowired
    private HistorialClinicoServicio historialServicio;

    @Autowired
    private MascotaService mascotaServicio;

    // VER TODOS LOS HISTORIALES (VETERINARIO)
    @GetMapping("/historiales")
    public String verHistoriales(
            @RequestParam(required = false) String buscar,
            Model model) {

        List<Mascota> mascotasFiltradas = new ArrayList<>();

        for (Mascota m : mascotaServicio.obtenerTodas()) {

            boolean coincide = false;

            if (buscar == null || buscar.isEmpty()) {

                coincide = true;

            } else {

                // BUSCAR POR NOMBRE DE MASCOTA
                if (m.getNombre().toLowerCase()
                        .contains(buscar.toLowerCase())) {

                    coincide = true;
                }

                // BUSCAR POR CÉDULA DEL DUEÑO
                if (m.getCedulaDueno() != null &&
                        m.getCedulaDueno().contains(buscar)) {

                    coincide = true;
                }
            }

            if (coincide) {

                mascotasFiltradas.add(m);
            }
        }

        model.addAttribute("mascotas", mascotasFiltradas);

        return "historiales";
    }

    // VER HISTORIAL DE UNA MASCOTA
    @GetMapping("/historial/{mascotaId}")
    public String verHistorial(
            @PathVariable Long mascotaId,
            Model model) {

        model.addAttribute("mascotaId", mascotaId);

        model.addAttribute(
                "historial",
                historialServicio.obtenerPorMascota(mascotaId));

        // CAMBIAR A FALSE CUANDO SEA CLIENTE
        model.addAttribute("esVeterinario", true);

        return "historial";
    }

    // GUARDAR REGISTRO MÉDICO
    @PostMapping("/historial/guardar")
    public String guardarHistorial(
            @RequestParam Long mascotaId,
            @RequestParam String tipo,
            @RequestParam String descripcion) {

        HistorialClinico h = new HistorialClinico();

        h.setMascotaId(mascotaId);

        h.setTipo(tipo);

        h.setDescripcion(descripcion);

        h.setFecha(LocalDate.now().toString());

        historialServicio.guardar(h);

        return "redirect:/historial/" + mascotaId;
    }
}