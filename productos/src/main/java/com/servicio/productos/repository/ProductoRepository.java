package com.servicio.productos.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.productos.model.Producto;

public interface ProductoRepository extends JpaRepository <Producto, Long>{


        Optional<Producto> findByProductoCategoria(long idCategoria);

}
