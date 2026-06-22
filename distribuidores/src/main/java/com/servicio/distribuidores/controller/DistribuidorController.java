package com.servicio.distribuidores.controller;

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

import com.servicio.distribuidores.config.ErrorResponse;
import com.servicio.distribuidores.model.Distribuidor;
import com.servicio.distribuidores.service.DistribuidorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/distribuidores")
@Tag(name = "Distribuidores", description = "Operaciones relacionadas con la gestión de distribuidores")
public class DistribuidorController {
    
    @Autowired
    private DistribuidorService distribuidorService;

    @Operation(summary = "Obtener todos los distribuidores", description ="Retorna una lista completa de los distribuidores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
        @ApiResponse(responseCode = "404", description = "No existe el paciente que busca", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public List<Distribuidor> listar(){
        return distribuidorService.listar();
    }

    @Operation(summary = "Obtener un distribuidor mediante su Id", description ="Retorna el distribuidor cuyo Id coincide con el ingresado")
    
    @GetMapping("/{id}")
    public ResponseEntity<Distribuidor> buscarPorId(@PathVariable Long id){
        return distribuidorService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new RuntimeException("Distribuidor no encontrado"));
    }

    @Operation(summary = "Crear un distribuidor", description ="Crea un distribuidor en base a los datos ingresados")
    @PostMapping("/{id}")
    public Distribuidor guardar(@Valid @RequestBody Distribuidor distribuidor){
        return distribuidorService.guardarDistribuidor(distribuidor);
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Distribuidor> modificar(@PathVariable Long id, @RequestBody Distribuidor distribuidor){
    //     try{
    //         ResponseEntity<Distribuidor> distri = distribuidorService.buscarPorId(id)
    //         .map(ResponseEntity::ok)
    //         .orElse(ResponseEntity.notFound().build());

    //         distri.getBody().setRutEmpresa(distri.getBody().getRutEmpresa());
    //         distri.getBody().setRazonSocial(distri.getBody().getRazonSocial());
    //         distri.getBody().setTelefono(distri.getBody().getTelefono());
    //         distri.getBody().setEmail(distri.getBody().getEmail());
    //         distri.getBody().setCalleDireccion(distri.getBody().getCalleDireccion());
    //         distri.getBody().setNumeroDireccion(distri.getBody().getNumeroDireccion());

    //         distribuidorService.guardarDistribuidor(distri.getBody());

    //         return distri;
    //     } catch(Exception e) {
    //         return ResponseEntity.notFound().build();
    //     }

    // }

    @Operation(summary = "Borrar un distribuidor", description ="Borra el distribuidor cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        distribuidorService.eliminarDistribuidor(id);
        return ResponseEntity.notFound().build();
    }
}
