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

import com.servicio.carrito.model.CarritoDetalle;
import com.servicio.carrito.service.CarritoDetalleService;

import io.swagger.v3.oas.annotations.media.Schema;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/carritos/carritosdetalle")
@Schema(description = "Modelo que representa los detalles de un carrito en el sistema")
public class CarritoDetalleController {

    @Autowired
    private CarritoDetalleService carritoDetalleService;

    @GetMapping
    public List<CarritoDetalle> listar(){
        return carritoDetalleService.listar();
    }

    @GetMapping("/{id}")
    public CarritoDetalle buscarPorId(@PathVariable Long id){
        return carritoDetalleService.buscarPorId(id);
    }

    @PostMapping
    public CarritoDetalle guardar(@RequestBody CarritoDetalle carritoDetalle){
        return carritoDetalleService.crearCarritoDetalle(carritoDetalle);
    }

    @PutMapping("/{id}")
    public CarritoDetalle modificar(@PathVariable Long id, @RequestBody CarritoDetalle carritoDetalle){
       
            CarritoDetalle carde = carritoDetalleService.buscarPorId(id);
            
            carde.setCantidad(carritoDetalle.getCantidad());
            
            
            carritoDetalleService.crearCarritoDetalle(carde);

            return carde;
            
        
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        carritoDetalleService.eliminarCarritoDetalle(id);
        return ResponseEntity.noContent().build();
    }

}
