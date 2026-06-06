package com.servicio.comprobantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.comprobantes.model.Comprobantes;

public interface ComprobantesRepository extends JpaRepository<Comprobantes, Long> {

}
