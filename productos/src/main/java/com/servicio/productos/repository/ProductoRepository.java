package com.servicio.productos.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.productos.model.Producto;
import com.servicio.productos.model.ProductoCategoria;

import java.util.List;


public interface ProductoRepository extends JpaRepository <Producto, Long>{


        List<Producto> findByProductoCategoria(ProductoCategoria productoCategoria);

}
