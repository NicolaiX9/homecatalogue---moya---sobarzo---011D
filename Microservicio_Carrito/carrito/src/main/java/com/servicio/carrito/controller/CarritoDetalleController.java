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

import com.servicio.carrito.model.CarritoDetalle;
import com.servicio.carrito.service.CarritoDetalleService;

@RestController
@RequestMapping("/api/v1/carritos/carritosdetalle")
public class CarritoDetalleController {

    @Autowired
    private CarritoDetalleService carritoDetalleService;

    @GetMapping
    public List<CarritoDetalle> listar(){
        return carritoDetalleService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDetalle> buscarPorId(Long id){
        return carritoDetalleService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping CarritoDetalle guardar(@RequestBody CarritoDetalle carritoDetalle){
        return carritoDetalleService.crearCarritoDetalle(carritoDetalle);
    }


    @PutMapping("/{idProducto}")
    public ResponseEntity<CarritoDetalle> actualizar(@PathVariable Long id, @RequestBody CarritoDetalle carritoDetalle){
        try {
            ResponseEntity<CarritoDetalle> car = carritoDetalleService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

            car.getBody().setCantidad(carritoDetalle.getCantidad());
            car.getBody().setProductoId(carritoDetalle.getProductoId());
            car.getBody().setCarrito(carritoDetalle.getCarrito());
            
            
            carritoDetalleService.crearCarritoDetalle(carritoDetalle);

            return ResponseEntity.ok(carritoDetalle);
            

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarCarrito(CarritoDetalle carritoDetalle){
        carritoDetalleService.eliminarCarrito(carritoDetalle);
    }
}
