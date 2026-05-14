package com.veterinaria.veterinaria.service;

import com.veterinaria.veterinaria.model.Factura;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaService {

    private List<Factura> facturas = new ArrayList<>();

    private Long consecutivo = 1L;

    public Factura guardar(Factura factura) {

        factura.setId(consecutivo++);

        factura.calcularTotal();

        facturas.add(factura);

        return factura;
    }

    public List<Factura> obtenerTodas() {
        return facturas;
    }
    public List<Factura> obtenerPorDocumento(String documento) {

        List<Factura> lista = new ArrayList<>();

        for (Factura f : facturas) {

            if (f.getDocumentoCliente()
                    .equals(documento)) {

                lista.add(f);
            }
        }

        return lista;
    }

}