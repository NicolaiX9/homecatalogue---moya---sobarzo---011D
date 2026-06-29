package com.servicio.comprobantes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.servicio.comprobantes.model.Comprobantes;
import com.servicio.comprobantes.service.ComprobantesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/comprobantes")
@Schema(description = "Modelo que representa un comprobante en el sistema")
@Tag(name = "Comprobantes", description = "Operaciones relacionadas con la gestión de comprobantes")
public class ComprobantesController {

    @Autowired
    private ComprobantesService comprobantesService;

    @Operation(summary = "Obtener todos los comprobantes", description ="Retorna una lista completa de los comprobantes")
    @GetMapping
    public List<Comprobantes> listar(){
        return comprobantesService.listar();
    }
    
    @Operation(summary = "Crear un comprobante", description ="Crea un comprobante en base a los datos ingresados")
    @PostMapping("/{id}")
    public Comprobantes guardar(@Valid @RequestBody Comprobantes comprobantes){
        return comprobantesService.guardar(comprobantes);
    }

    @Operation(summary = "Borrar un comprobante", description ="Borra el comprobante cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id ){
        comprobantesService.eliminar(id);
        return ResponseEntity.notFound().build();
    }
}
