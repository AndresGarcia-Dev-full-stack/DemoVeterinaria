package com.veterinaria.veterinaria.controller;

import com.veterinaria.veterinaria.model.Factura;
import com.veterinaria.veterinaria.model.ItemFactura;
import com.veterinaria.veterinaria.service.FacturaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/crear")
    public String vistaFactura() {
        return "crear-factura";
    }

    @PostMapping("/guardar")
    public String guardarFactura(@RequestParam String cliente,
                                 @RequestParam String mascota,
                                 @RequestParam String servicio,
                                 @RequestParam int cantidad,
                                 @RequestParam double valor,
                                 @RequestParam String documentoCliente,
                                 Model model) {

        Factura factura = new Factura();

        factura.setCliente(cliente);
        factura.setMascota(mascota);
        factura.setDocumentoCliente(documentoCliente);

        ItemFactura item = new ItemFactura();

        item.setServicio(servicio);
        item.setCantidad(cantidad);
        item.setValor(valor);

        factura.getItems().add(item);

        facturaService.guardar(factura);

        model.addAttribute("mensaje", "Factura creada correctamente");

        return "crear-factura";
    }

    @GetMapping("/listar")
    public String listarFacturas(Model model) {

        model.addAttribute(
                "facturas",
                facturaService.obtenerTodas());

        return "facturas";
    }
}