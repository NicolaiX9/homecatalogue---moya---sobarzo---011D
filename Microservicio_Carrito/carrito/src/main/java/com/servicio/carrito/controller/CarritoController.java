package com.servicio.carrito.controller;

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

import com.servicio.carrito.model.Carrito;
import com.servicio.carrito.service.CarritoService;

@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<Carrito> listar(){
        return carritoService.listar();
    }

    @GetMapping("/{idCarrito}")
    public ResponseEntity<Carrito> buscarPorId(Long id){
        return carritoService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrito crearCarrito(Carrito carrito){
        return carritoService.crearCarrito(carrito);
    }

    @PutMapping("/{idCarrito}")
    public ResponseEntity<Carrito> actualizar(@PathVariable Long id, @RequestBody Carrito carrito){
        try {
            ResponseEntity<Carrito> car = carritoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

            car.getBody().setId_usuario(carrito.getId_usuario());
            car.getBody().setTotal(carrito.getTotal());
            
            
            carritoService.crearCarrito(carrito);

            return ResponseEntity.ok(carrito);
            

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{idCarrito}")
    public void eliminarCarrito(Carrito carrito){
        carritoService.eliminarCarrito(carrito);
    }

    
}
