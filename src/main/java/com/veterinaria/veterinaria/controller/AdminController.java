package com.veterinaria.veterinaria.controller;

import com.veterinaria.veterinaria.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.veterinaria.veterinaria.model.Mascota;
import com.veterinaria.veterinaria.service.MascotaService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsuarioService usuarioService;
    private final MascotaService mascotaService;

    /*public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }*/
    public AdminController(UsuarioService usuarioService,
                           MascotaService mascotaService) {

        this.usuarioService = usuarioService;
        this.mascotaService = mascotaService;
    }

    @GetMapping("/registro-mascota")
    public String vistaRegistroMascota(Model model) {

        model.addAttribute("mascota", new Mascota());

        return "registro-mascota-admin";
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

        return "registro-mascota-admin";
    }

    @GetMapping("/solicitudes-veterinarios")
    public String verSolicitudes(Model model) {
        model.addAttribute("veterinarios", usuarioService.obtenerVeterinariosPendientes());
        return "solicitudes-veterinarios";
    }

    @PostMapping("/aprobar/{id}")
    public String aprobar(@PathVariable Long id) {
        usuarioService.aprobarVeterinario(id);
        return "redirect:/admin/solicitudes-veterinarios";
    }

    @PostMapping("/rechazar/{id}")
    public String rechazar(@PathVariable Long id) {
        usuarioService.rechazarVeterinario(id);
        return "redirect:/admin/solicitudes-veterinarios";
    }
}