package com.servicio.distribuidores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.distribuidores.model.Distribuidor;

public interface DistribuidorRepository extends JpaRepository<Distribuidor, Long> {

}
