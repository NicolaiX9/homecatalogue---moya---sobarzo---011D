package com.servicio.usuario.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.usuario.model.Usuario;
import com.servicio.usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;    

    @Operation(summary = "Obtener todos los usuarios", description ="Retorna una lista completa de los usuarios")
    @GetMapping
    public List <Usuario> listar(){
        return usuarioService.listarUsuarios();
    }

    @Operation(summary = "Obtener un usuario mediante su Id", description ="Retorna el usuario cuya Id coincide con la ingresada")
    @GetMapping("/{id}")
    public ResponseEntity <Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Crear un usuario", description ="Crea un usuario en base a los datos ingresados")
    @PostMapping ResponseEntity <Usuario> guardar(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.guardarUsuario(usuario));
    }

    @Operation(summary = "Borrar un usuario", description ="Borra el usuario cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener un usuario mediante su run", description ="Retorna el usuario cuyo run coincide con el ingresado")
    @GetMapping("/run/{run}")
    public ResponseEntity<Usuario> buscarPorRun(@PathVariable String run){
      return usuarioService.encontrarPorRun(run)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un usuario", description ="Actualiza un usuario en base a los datos ingresados")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificar(@PathVariable Long id, @RequestBody Usuario usuario){
       try {
            ResponseEntity <Usuario> user = usuarioService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
            
            
            user.getBody().setEmail(usuario.getEmail());
            user.getBody().setPassword(usuario.getPassword());
            
            usuarioService.guardarUsuario(user.getBody());

            return user;
            } catch (Exception e){
            return ResponseEntity.notFound().build();
        } 
        
    }
    

}
