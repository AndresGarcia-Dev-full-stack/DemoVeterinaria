package com.veterinaria.veterinaria.controller;

import com.veterinaria.veterinaria.model.Mascota;
import com.veterinaria.veterinaria.service.MascotaService;
import com.veterinaria.veterinaria.service.TarifaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {

    private final MascotaService mascotaService;
    private final TarifaService tarifaService;

    public VeterinarioController(MascotaService mascotaService,
                                 TarifaService tarifaService) {

        this.mascotaService = mascotaService;
        this.tarifaService = tarifaService;
    }

    @GetMapping("/inicio")
    public String inicioVeterinario() {

        return "veterinario-inicio";
    }

    @GetMapping("/registro-mascota")
    public String vistaRegistro(Model model) {

        model.addAttribute("mascota", new Mascota());

        return "registro-mascota";
    }

    @PostMapping("/registro-mascota")
    public String registrarMascota(@ModelAttribute Mascota mascota,
                                   @RequestParam String documentoCliente,
                                   Model model) {

        String msg =
                mascotaService.registrarMascota(
                        mascota,
                        documentoCliente);

        model.addAttribute("mensaje", msg);

        return "registro-mascota";
    }

    @GetMapping("/ver-mascotas")
    public String verMascotas(Model model) {

        model.addAttribute(
                "mascotas",
                mascotaService.obtenerTodas());

        return "ver-mascotas";
    }

    @PostMapping("/buscar-mascotas")
    public String buscarMascotas(@RequestParam String filtro,
                                 @RequestParam String tipo,
                                 Model model) {

        List<Mascota> mascotas;

        if (tipo.equals("documento")) {

            mascotas =
                    mascotaService.buscarPorDocumento(filtro);

        } else {

            mascotas =
                    mascotaService.buscarPorNombre(filtro);
        }

        model.addAttribute("mascotas", mascotas);

        return "ver-mascotas";
    }

    @GetMapping("/tarifas")
    public String verTarifas(Model model) {

        model.addAttribute(
                "servicios",
                tarifaService.obtenerTodos());

        return "tarifas-veterinario";
    }
}