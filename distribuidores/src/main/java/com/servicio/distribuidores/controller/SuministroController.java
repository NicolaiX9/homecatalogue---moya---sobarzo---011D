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
import com.servicio.distribuidores.model.Suministro;
import com.servicio.distribuidores.service.SuministroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/distribuidores/suministros")
@Tag(name = "Suministros", description = "Operaciones relacionadas con la gestión de los suministros de los distribuidores")
public class SuministroController {
    @Autowired
    private SuministroService suministroService;

    @Operation(summary = "Obtener todos los suministros de un producto", description ="Retorna una lista completa de los suministros de un producto")
    @GetMapping
    public List<Suministro> listar(){
        return suministroService.listar();
    }

    @Operation(summary = "Obtener un suministro mediante su Id", description ="Retorna el suministro cuyo Id coincide con el ingresado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
        @ApiResponse(responseCode = "404", description = "No existe el paciente que busca", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public Suministro buscarPorId(@PathVariable Long id){
        return suministroService.buscarPorId(id);
    }

    @Operation(summary = "Crear un suministro. Al ingresar los datos del distribuidor se debe ingresar solo el id de este (el campo se escribe id, a secas).", description ="Crea el suministro de un producto en base a los datos ingresados")
    @PostMapping
    public Suministro guardar(@Valid @RequestBody Suministro suministro){
        return suministroService.crearSuministro(suministro);
    }


    @Operation(summary = "Borrar un suministro", description ="Borra el suministro cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        suministroService.eliminarSuministro(id);
        return ResponseEntity.notFound().build();
    }
}
