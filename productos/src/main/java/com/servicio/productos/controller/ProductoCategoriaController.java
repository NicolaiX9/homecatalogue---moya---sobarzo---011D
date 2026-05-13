package com.servicio.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ProductoCategoria crear(ProductoCategoria productoCategoria){
        return productoCategoriaService.guardar(productoCategoria);
    }

    @PutMapping("/idCategoria")
    public ResponseEntity<ProductoCategoria> actualizar(@PathVariable Long idCategoria, @RequestBody ProductoCategoria productoCategoria){
        try {
            ResponseEntity<ProductoCategoria> prodcat = productoCategoriaService.findById(idCategoria)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

            prodcat.getBody().setCategoria(productoCategoria.getCategoria());
            
            productoCategoriaService.guardar(productoCategoria);

            return ResponseEntity.ok(productoCategoria);
            

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{idCategoria}")
    public void eliminar(ProductoCategoria productoCategoria){
        productoCategoriaService.eliminar(productoCategoria);
    }
}
