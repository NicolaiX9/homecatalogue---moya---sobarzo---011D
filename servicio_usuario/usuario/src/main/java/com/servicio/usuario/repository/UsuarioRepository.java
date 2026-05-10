package com.servicio.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

  
}
