package com.servicio.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.usuario.model.TipoUsuario;


public interface TipoUsuarioRepository extends JpaRepository <TipoUsuario, Long> {

  

}
