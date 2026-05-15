package com.servicio.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.productos.model.ProductoCategoria;
import com.servicio.productos.service.ProductoCategoriaService;

@RestController
@RequestMapping("/productos/categoria")
public class ProductoCategoriaController {

    @Autowired
    private ProductoCategoriaService productoCategoriaService;

    @GetMapping
    public List<ProductoCategoria> listar(ProductoCategoria productoCategoria){
        return productoCategoriaService.listar(productoCategoria);
    }

    @PostMapping
    public ProductoCategoria guardar(ProductoCategoria productoCategoria){
        return productoCategoriaService.guardar(productoCategoria);
    }

    @DeleteMapping("/{idCategoria}")
    public void eliminar(ProductoCategoria productoCategoria){
        productoCategoriaService.eliminar(productoCategoria);
    }
}
