package com.veterinaria.veterinaria.service;

import com.veterinaria.veterinaria.model.Usuario;
import com.veterinaria.veterinaria.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String registrarCliente(Usuario usuario) {

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            return "El correo ya se encuentra registrado.";
        }

        if (usuarioRepository.existsByNumeroDocumento(usuario.getNumeroDocumento())) {
            return "El número de documento ya se encuentra registrado.";
        }

        usuario.setRol("CLIENTE");
        usuario.setEstado("ACTIVO");

        usuarioRepository.save(usuario);

        return "Cliente registrado exitosamente.";
    }

    public String registrarVeterinario(Usuario usuario) {

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            return "El correo ya se encuentra registrado.";
        }

        if (usuarioRepository.existsByNumeroDocumento(usuario.getNumeroDocumento())) {
            return "El número de documento ya se encuentra registrado.";
        }

        usuario.setRol("VETERINARIO");
        usuario.setEstado("PENDIENTE");

        usuarioRepository.save(usuario);

        return "Solicitud enviada correctamente.";
    }

    public Usuario login(String correo, String password) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);

        if (usuarioOpt.isEmpty()) return null;

        Usuario usuario = usuarioOpt.get();

        if (!usuario.getPassword().equals(password)) return null;

        if (!usuario.getEstado().equals("ACTIVO")) return null;

        return usuario;
    }

    public List<Usuario> obtenerVeterinariosPendientes() {
        return usuarioRepository.findByRolAndEstado("VETERINARIO", "PENDIENTE");
    }

    public void aprobarVeterinario(Long id) {
        usuarioRepository.findById(id).ifPresent(u -> {
            u.setEstado("ACTIVO");
            usuarioRepository.save(u);
        });
    }

    public void rechazarVeterinario(Long id) {
        usuarioRepository.deleteById(id);
    }
}