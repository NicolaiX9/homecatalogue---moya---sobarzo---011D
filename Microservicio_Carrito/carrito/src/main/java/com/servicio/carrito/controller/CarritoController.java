package com.servicio.carrito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.carrito.model.Carrito;
import com.servicio.carrito.service.CarritoService;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/carritos")
@Tag(name = "Carritos", description = "Operaciones relacionadas con la gestión de carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> listar(){
        return carritoService.listar();
    }

    @GetMapping("/{id}")
    public Carrito buscarPorId(@PathVariable Long id){
        return carritoService.buscarPorId(id);
    }

    @PostMapping
    public Carrito guardar(@RequestBody Carrito carrito){
        return carritoService.crearCarrito(carrito);
    }

    @PutMapping("/{id}")
    public Carrito modificar(@PathVariable Long id, @RequestBody Carrito carrito){
       
            Carrito car = carritoService.buscarPorId(id);
            
            car.setTotal(carrito.getTotal());
            
            
            carritoService.crearCarrito(car);

            return car;
            
        
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    
    }

    
}
