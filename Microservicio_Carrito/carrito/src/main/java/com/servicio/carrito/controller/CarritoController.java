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
import com.servicio.carrito.repository.CarritoRepository;

@RestController
@RequestMapping("/api/v1/carrito")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    @GetMapping
    public List<Carrito> listar(){
        return carritoRepository.findAll();
    }

    @PostMapping
    public Carrito crearCarrito(Carrito carrito){
        return carritoRepository.save(carrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable Long id, @RequestBody Carrito carritoDetalles) {
        return carritoRepository.findById(id)
    // cambiar esto a por uno q hemos visto.
        .map(carritoExistente -> {
            carritoExistente.setId_usuario(carritoDetalles.getId_usuario());
            carritoExistente.setTotal(carritoDetalles.getTotal());

            Carrito actualizado = carritoRepository.save(carritoExistente);
            return ResponseEntity.ok(actualizado);
        })
        .orElse(ResponseEntity.notFound().build());
}

    @DeleteMapping("/{id}")
    public void eliminarCarrito(Carrito carrito){
        carritoRepository.delete(carrito);
    }

    
}
