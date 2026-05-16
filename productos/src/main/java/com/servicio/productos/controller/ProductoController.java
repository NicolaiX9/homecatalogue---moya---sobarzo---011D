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

import com.servicio.productos.model.Producto;
import com.servicio.productos.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listar(){
        return productoService.listar();
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto producto){
        return productoService.crearProducto(producto);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Producto> buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificar(@PathVariable Long id, @RequestBody Producto producto){
        try {
            ResponseEntity<Producto> prod = productoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

      
            prod.getBody().setPrecio(producto.getPrecio());
            

            productoService.crearProducto(prod.getBody());

            return ResponseEntity.ok(prod.getBody());
            

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtrarCategoria/{id}")
    public List<Producto> buscarPorCategoria(@PathVariable Long id) {
        return productoService.buscarPorCategoria(id);
    }
}
