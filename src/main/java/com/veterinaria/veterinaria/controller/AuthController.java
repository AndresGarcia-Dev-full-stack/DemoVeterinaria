package com.veterinaria.veterinaria.controller;

import com.veterinaria.veterinaria.model.Usuario;
import com.veterinaria.veterinaria.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    private int intentosFallidos = 0; // contador simple

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping({"/", "/login"})
    public String login(Model model) {
        if (intentosFallidos >= 2) {
            model.addAttribute("mostrarCaptcha", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam String correo,
                                @RequestParam String password,
                                @RequestParam(required = false) String captcha,
                                @RequestParam(required = false) String captchaRespuesta,
                                Model model) {

        // Si ya falló 2 veces → validar captcha
        if (intentosFallidos >= 2) {
            if (captcha == null || captchaRespuesta == null || !captcha.equals(captchaRespuesta)) {
                model.addAttribute("error", "Captcha incorrecto");
                model.addAttribute("mostrarCaptcha", true);
                return "login";
            }
        }

        Usuario usuario = usuarioService.login(correo, password);

        if (usuario == null) {
            intentosFallidos++;

            model.addAttribute("error", "Credenciales incorrectas");

            if (intentosFallidos >= 2) {
                model.addAttribute("mostrarCaptcha", true);
            }

            return "login";
        }

        // Login exitoso → reset
        intentosFallidos = 0;

        if (usuario.getRol().equals("ADMIN")) {
            return "redirect:/admin/solicitudes-veterinarios";
        }

        if (usuario.getRol().equals("VETERINARIO")) {
            return "redirect:/veterinario/inicio";
        }

        if (usuario.getRol().equals("CLIENTE")) {
            return "redirect:/cliente/inicio";
        }

        return "bienvenida";


    }

    @GetMapping("/registro-cliente")
    public String vistaCliente(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro-cliente";
    }

    /*@PostMapping("/registro-cliente")
    public String registrarCliente(@ModelAttribute Usuario usuario, Model model) {

        String msg = usuarioService.registrarCliente(usuario);

        if (msg.contains("ya")) {
            model.addAttribute("error", msg);
            return "registro-cliente";
        }

        model.addAttribute("mensaje", msg);
        return "login";
    }*/
    @PostMapping("/registro-cliente")
    public String registrarCliente(@ModelAttribute Usuario usuario,
                                   @RequestParam String confirmarPassword,
                                   @RequestParam String captcha,
                                   @RequestParam String captchaRespuesta,
                                   Model model) {

        if (!usuario.getPassword().equals(confirmarPassword)) {

            model.addAttribute("error",
                    "Las contraseñas no coinciden");

            return "registro-cliente";
        }

        if (!captcha.equals(captchaRespuesta)) {

            model.addAttribute("error",
                    "Captcha incorrecto");

            return "registro-cliente";
        }

        String msg = usuarioService.registrarCliente(usuario);

        if (msg.contains("ya")) {

            model.addAttribute("error", msg);

            return "registro-cliente";
        }

        model.addAttribute("mensaje", msg);

        return "login";
    }

    @GetMapping("/registro-veterinario")
    public String vistaVet(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro-veterinario";
    }

    @PostMapping("/registro-veterinario")
    public String registrarVet(@ModelAttribute Usuario usuario, Model model) {

        String msg = usuarioService.registrarVeterinario(usuario);

        if (msg.contains("ya")) {
            model.addAttribute("error", msg);
            return "registro-veterinario";
        }

        model.addAttribute("mensaje", msg);
        return "login";
    }

    @GetMapping("/olvide-password")
    public String olvide() {
        return "olvide-password";
    }
}