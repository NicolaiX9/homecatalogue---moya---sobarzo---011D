package com.servicio.transportista.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.transportista.model.Transportista;

public interface TransportistaRepository extends JpaRepository <Transportista, Long>{

    Transportista findByRut(String rut);


}
