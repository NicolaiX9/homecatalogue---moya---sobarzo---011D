package com.servicio.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listar(){
        return productoService.listar();
    }

    @PostMapping
    public Producto crear(Producto producto){
        return productoService.crearProducto(producto);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto){
        try {
            ResponseEntity<Producto> prod = productoService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

            prod.getBody().setNombre(producto.getNombre());
            prod.getBody().setDescripcion(producto.getDescripcion());
            prod.getBody().setPrecio(producto.getPrecio());
            prod.getBody().setId(producto.getId());
            
            productoService.crearProducto(producto);

            return ResponseEntity.ok(producto);
            

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    public void eliminar(Producto producto){
        productoService.eliminarProducto(producto);
    }

}
