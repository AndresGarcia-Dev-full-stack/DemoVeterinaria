package com.veterinaria.veterinaria.controller;

import com.veterinaria.veterinaria.model.Mascota;
import com.veterinaria.veterinaria.service.MascotaService;
import com.veterinaria.veterinaria.service.FacturaService;
import com.veterinaria.veterinaria.service.CitaService;
import com.veterinaria.veterinaria.service.HistorialClinicoServicio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.veterinaria.veterinaria.model.Cita;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final MascotaService mascotaService;

    private final FacturaService facturaService;

    private final CitaService citaService;

    private final HistorialClinicoServicio historialClinicoServicio;

    public ClienteController(MascotaService mascotaService,
                             FacturaService facturaService,
                             CitaService citaService,
                             HistorialClinicoServicio historialClinicoServicio) {

        this.mascotaService = mascotaService;
        this.facturaService = facturaService;
        this.citaService = citaService;
        this.historialClinicoServicio = historialClinicoServicio;
    }

    @GetMapping("/inicio")
    public String inicioCliente() {

        return "cliente-inicio";
    }

    @GetMapping("/mis-facturas")
    public String verFacturas(@RequestParam(required = false) String documentoCliente,
                              Model model) {

        if (documentoCliente != null && !documentoCliente.isEmpty()) {

            model.addAttribute(
                    "facturas",
                    facturaService.obtenerPorDocumento(documentoCliente));

            model.addAttribute("documentoCliente", documentoCliente);
        }

        return "mis-facturas";
    }

    @GetMapping("/mis-mascotas")
    public String verMascotas() {

        return "buscar-mascotas";
    }

    @PostMapping("/mis-mascotas")
    public String buscarMascotas(@RequestParam String documento,
                                 Model model) {

        List<Mascota> mascotas =
                mascotaService.obtenerMascotasCliente(documento);

        model.addAttribute("mascotas", mascotas);

        return "mis-mascotas";
    }

    @GetMapping("/agendar-cita")
    public String agendarCita(Model model) {

        model.addAttribute(
                "citas",
                citaService.obtenerTodas());

        return "agendar-cita";
    }
    @PostMapping("/agendar-cita")
    public String guardarCita(@ModelAttribute Cita cita,
                              Model model) {

        citaService.guardar(cita);

        model.addAttribute(
                "citas",
                citaService.obtenerTodas());

        return "agendar-cita";
    }

    @GetMapping("/mi-historial")
    public String verMiHistorial(Model model) {

        model.addAttribute(
                "citas",
                citaService.obtenerTodas());

        model.addAttribute(
                "historial",
                historialClinicoServicio.obtenerTodos());

        return "mi-historial";
    }
}