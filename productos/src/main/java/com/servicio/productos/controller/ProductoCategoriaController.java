package com.servicio.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.productos.model.ProductoCategoria;
import com.servicio.productos.service.ProductoCategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/productos/categoria")
@Tag(name = "Categorías de producto", description = "Operaciones relacionadas con la gestión de las categorías de los productos")
public class ProductoCategoriaController {

    @Autowired
    private ProductoCategoriaService productoCategoriaService;

    @Operation(summary = "Obtener todas las categorías de productos", description ="Retorna una lista completa de las categorías de productos")
    @GetMapping
    public List<ProductoCategoria> listar(){
        return productoCategoriaService.listar();
    }

    @Operation(summary = "Crear la categoría de un producto", description ="Crea la categoría de un producto en base a los datos ingresados")
    @PostMapping
    public ProductoCategoria guardar(@Valid @RequestBody ProductoCategoria productoCategoria){
        return productoCategoriaService.guardar(productoCategoria);
    }

    @Operation(summary = "Borrar una categoría de producto", description ="Borra la categoría de producto cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        productoCategoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
