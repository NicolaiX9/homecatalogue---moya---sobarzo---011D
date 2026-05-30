package com.servicio.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.ventas.model.Venta;

public interface VentaRepository extends JpaRepository <Venta, Long>{

}
