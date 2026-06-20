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

import io.swagger.v3.oas.annotations.media.Schema;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/usuarios/tipo")
@Schema(description = "Modelo que representa un tipo de usuario en el sistema")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    

    @GetMapping
    public List <TipoUsuario> listar(){
        return tipoUsuarioRepository.findAll();
    }
    
    @PostMapping TipoUsuario guardar(@RequestBody TipoUsuario tipo){
        return tipoUsuarioRepository.save(tipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        tipoUsuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }





}
