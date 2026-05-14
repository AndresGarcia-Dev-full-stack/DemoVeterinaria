package com.veterinaria.veterinaria.controller;

import com.veterinaria.veterinaria.model.ServicioVeterinario;
import com.veterinaria.veterinaria.service.TarifaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tarifas")
public class TarifaController {

    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService) {

        this.tarifaService = tarifaService;
    }

    @GetMapping
    public String vistaTarifas(Model model) {

        model.addAttribute(
                "servicios",
                tarifaService.obtenerTodos());

        return "tarifas";
    }

    @PostMapping("/guardar")
    public String guardar(
            @RequestParam String nombre,
            @RequestParam double tarifa) {

        ServicioVeterinario servicio =
                new ServicioVeterinario();

        servicio.setNombre(nombre);

        servicio.setTarifa(tarifa);

        tarifaService.guardar(servicio);

        return "redirect:/admin/tarifas";
    }
}