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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/carritos")
@Tag(name = "Carritos", description = "Operaciones relacionadas con la gestión de carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Operation(summary = "Obtener todos los carritos", description ="Retorna una lista completa de carritos registrados")
    @GetMapping
    public List<Carrito> listar(){
        return carritoService.listar();
    }

    @Operation(summary = "Obtener un carrito mediante su Id", description ="Retorna el carrito cuyo Id coincide con el ingresado")
    @GetMapping("/{id}")
    public Carrito buscarPorId(@PathVariable Long id){
        return carritoService.buscarPorId(id);
    }

    @Operation(summary = "Crear un carrito", description ="Crea un carrito en base a los datos ingresados")
    @PostMapping
    public Carrito guardar(@Valid @RequestBody Carrito carrito){
        return carritoService.crearCarrito(carrito);
    }

    @Operation(summary = "Actualizar un carrito", description ="Actualiza un carrito en base a los datos ingresados")
    @PutMapping("/{id}")
    public Carrito modificar(@Valid @PathVariable Long id, @RequestBody Carrito carrito){
       
            Carrito car = carritoService.buscarPorId(id);
            
            car.setTotal(carrito.getTotal());
            
            
            carritoService.crearCarrito(car);

            return car;
            
        
    }

    @Operation(summary = "Borrar un carrito", description ="Borra el carrito cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    
    }

    
}
