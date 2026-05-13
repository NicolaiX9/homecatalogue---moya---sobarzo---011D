package com.servicio.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.productos.model.ProductoCategoria;

public interface ProductoCategoriaRepository extends JpaRepository<ProductoCategoria, Long> {

}
