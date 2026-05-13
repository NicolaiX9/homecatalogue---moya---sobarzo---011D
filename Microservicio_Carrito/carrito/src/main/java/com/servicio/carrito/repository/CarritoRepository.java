package com.servicio.carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.carrito.model.Carrito;


public interface CarritoRepository extends JpaRepository<Carrito, Long> {

}
