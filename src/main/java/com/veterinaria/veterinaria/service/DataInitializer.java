package com.veterinaria.veterinaria.service;

import com.veterinaria.veterinaria.model.Usuario;
import com.veterinaria.veterinaria.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public DataInitializer(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {

        if (!usuarioRepository.existsByCorreo("admin@veterinaria.com")) {

            Usuario admin = new Usuario();
            admin.setNombres("Admin");
            admin.setApellidos("Principal");
            admin.setTipoDocumento("CC");
            admin.setNumeroDocumento("0000");
            admin.setCorreo("admin@veterinaria.com");
            admin.setPassword("Admin123*");
            admin.setRol("ADMIN");
            admin.setEstado("ACTIVO");

            usuarioRepository.save(admin);
        }
    }
}