package com.servicio.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.productos.model.ProductoCategoria;
import com.servicio.productos.service.ProductoCategoriaService;

@RestController
@RequestMapping("/api/v1/productos/categoria")
public class ProductoCategoriaController {

    @Autowired
    private ProductoCategoriaService productoCategoriaService;

    @GetMapping
    public List<ProductoCategoria> listar(){
        return productoCategoriaService.listar();
    }

    @PostMapping
    public ProductoCategoria guardar(@RequestBody ProductoCategoria productoCategoria){
        return productoCategoriaService.guardar(productoCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        productoCategoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
