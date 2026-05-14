package com.veterinaria.veterinaria.service;

import com.veterinaria.veterinaria.model.Mascota;
import com.veterinaria.veterinaria.model.Usuario;
import com.veterinaria.veterinaria.repository.MascotaRepository;
import com.veterinaria.veterinaria.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final UsuarioRepository usuarioRepository;

    public MascotaService(MascotaRepository mascotaRepository,
                          UsuarioRepository usuarioRepository) {

        this.mascotaRepository = mascotaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public String registrarMascota(Mascota mascota, String documentoCliente) {

        Usuario cliente = usuarioRepository.findByNumeroDocumento(documentoCliente);

        if (cliente == null) {
            return "Cliente no encontrado";
        }

        if (!cliente.getRol().equals("CLIENTE")) {
            return "El usuario no es un cliente";
        }

        mascota.setCliente(cliente);

        mascotaRepository.save(mascota);

        return "Mascota registrada correctamente";
    }

    public List<Mascota> obtenerMascotasCliente(String documento) {


        return mascotaRepository.findByClienteNumeroDocumento(documento);
    }

    public List<Mascota> obtenerTodas() {
        return mascotaRepository.findAll();
    }

    public List<Mascota> buscarPorNombre(String nombre) {
        return mascotaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Mascota> buscarPorDocumento(String documento) {
        return mascotaRepository.findByClienteNumeroDocumentoContaining(documento);
    }



}