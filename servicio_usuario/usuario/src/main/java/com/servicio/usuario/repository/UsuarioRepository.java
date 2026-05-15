package com.servicio.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.usuario.model.Usuario;



public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

    Optional<Usuario> findByRun(String rut);

    
  
}
