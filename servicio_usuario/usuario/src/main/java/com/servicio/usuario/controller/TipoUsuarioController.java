package com.servicio.usuario.controller;

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

import com.servicio.usuario.model.TipoUsuario;
import com.servicio.usuario.repository.TipoUsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/usuarios/tipo")
@Tag(name = "Tipos de usuario", description = "Operaciones relacionadas con la gestión de los tipos de usuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    
    @Operation(summary = "Obtener todos los tipos de usuarios", description ="Retorna una lista completa de los tipos de usuarios")
    @GetMapping
    public List <TipoUsuario> listar(){
        return tipoUsuarioRepository.findAll();
    }
    
    @Operation(summary = "Crear un tipo de usuario", description ="Crea un tipo de usuario en base a los datos ingresados")
    @PostMapping TipoUsuario guardar(@Valid @RequestBody TipoUsuario tipo){
        return tipoUsuarioRepository.save(tipo);
    }

    @Operation(summary = "Borrar un tipo de usuario", description ="Borra el tipo de usuario cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        tipoUsuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }





}
