package com.veterinaria.veterinaria.repository;

import com.veterinaria.veterinaria.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    List<Mascota> findByClienteNumeroDocumento(String numeroDocumento);

    List<Mascota> findByNombreContainingIgnoreCase(String nombre);

    List<Mascota> findByClienteNumeroDocumentoContaining(String documento);
}