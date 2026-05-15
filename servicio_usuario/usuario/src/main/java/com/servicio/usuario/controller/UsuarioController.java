package com.servicio.usuario.controller;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;    


    @GetMapping
    public List <Usuario> listar(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Usuario> encontrarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping ResponseEntity <Usuario> guardar(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.guardarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        //El build() llama al constructor
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/run/{run}")
    public ResponseEntity<Usuario> encontrarPorRun(@PathVariable String run){
      return usuarioService.encontrarPorRun(run)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario){
       
            ResponseEntity <Usuario> user = usuarioService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
            
            
            user.getBody().setEmail(usuario.getEmail());
            user.getBody().setPassword(usuario.getPassword());
            
            usuarioService.guardarUsuario(user.getBody());

            return user;
            
        
    }
    

}
