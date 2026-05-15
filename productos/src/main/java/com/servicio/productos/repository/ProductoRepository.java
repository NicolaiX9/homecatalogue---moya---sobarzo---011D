package com.servicio.productos.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.productos.model.Producto;


import java.util.List;


public interface ProductoRepository extends JpaRepository <Producto, Long>{


        List<Producto> findByProductoCategoriaId(Long id);

}
