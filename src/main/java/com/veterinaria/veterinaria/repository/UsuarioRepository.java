package com.veterinaria.veterinaria.repository;

import com.veterinaria.veterinaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    boolean existsByNumeroDocumento(String numeroDocumento);

    List<Usuario> findByRolAndEstado(String rol, String estado);

    Usuario findByNumeroDocumento(String numeroDocumento);
}