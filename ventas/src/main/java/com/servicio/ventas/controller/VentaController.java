package com.servicio.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.ventas.model.Venta;
import com.servicio.ventas.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/ventas")
@Tag(name = "Ventas", description = "Operaciones relacionadas con la gestión de ventas")
public class VentaController {


    @Autowired
    private VentaService ventaService;

    @Operation(summary = "Obtener todas las ventas", description ="Retorna una lista completa de las ventas")
    @GetMapping
    public List<Venta> listar(){
        return ventaService.listar();
    }

    @Operation(summary = "Obtener una venta mediante su Id", description ="Retorna la venta cuya Id coincide con la ingresada")
    @GetMapping("/{id}")
    public Venta buscarPorId(@PathVariable Long id){
        return ventaService.buscarPorId(id);
    }

    @Operation(summary = "Crear una venta", description ="Crea una venta en base a los datos ingresados")
    @PostMapping
    public Venta guardar(@Valid @RequestBody Venta venta){
        return ventaService.crearVenta(venta);
    }



    @Operation(summary = "Borrar una venta", description ="Borra la venta cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }

}
