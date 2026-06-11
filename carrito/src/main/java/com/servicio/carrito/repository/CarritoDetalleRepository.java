package com.servicio.carrito.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.carrito.model.CarritoDetalle;

public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Long>{

}
