package com.servicio.despachos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.despachos.model.Despacho;

public interface DespachoRepository extends JpaRepository<Despacho, Long>{

}
