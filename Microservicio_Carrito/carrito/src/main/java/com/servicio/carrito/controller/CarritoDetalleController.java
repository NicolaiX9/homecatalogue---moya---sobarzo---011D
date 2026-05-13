package com.servicio.carrito.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.carrito.model.Carrito;
import com.servicio.carrito.model.CarritoDetalle;
import com.servicio.carrito.service.CarritoDetalleService;

@RestController
@RequestMapping("/api/v1/carritodetalle")
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

    @PutMapping("/{id}")
    public ResponseEntity<CarritoDetalle> actualizarCarrito(@PathVariable Long id, @RequestBody CarritoDetalle det) {
        try{
            ResponseEntity<CarritoDetalle> deta = buscarPorId(id);

            deta.getBody().setProductoId(id);
            deta.getBody().setCantidad(det.getCantidad());
            deta.getBody().setCarrito(det.getCarrito());
            

            carritoDetalleService.save(deta);
            return ResponseEntity.ok(CarritoDetalleService);


        }
       catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
}

    @DeleteMapping("/{id}")
    public void eliminarCarrito(CarritoDetalle carritoDetalle){
        carritoDetalleService.eliminarCarrito(carritoDetalle);
    }
}
