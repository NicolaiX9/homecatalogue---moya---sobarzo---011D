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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/carritos/carritosdetalle")
@Tag(name = "Detalle de carritos", description = "Operaciones relacionadas con la gestión del detalle de los carritos")
public class CarritoDetalleController {

    @Autowired
    private CarritoDetalleService carritoDetalleService;

    @Operation(summary = "Obtener todos los detalles de los carritos", description ="Retorna una lista completa de los detalles de los carritos registrados")
    @GetMapping
    public List<CarritoDetalle> listar(){
        return carritoDetalleService.listar();
    }


    @Operation(summary = "Obtener los detalles de un carrito mediante su Id", description ="Retorna el detalle del carrito cuyo Id coincide con el ingresado")
    @GetMapping("/{id}")
    public CarritoDetalle buscarPorId(@PathVariable Long id){
        return carritoDetalleService.buscarPorId(id);
    }

    @Operation(summary = "Crear los detalles de un carrito. Al ingresar los datos del carrito se debe ingresar solo el id de este (el campo se escribe id, a secas).", description ="Crea los detalles de un carrito en base a los datos ingresados")
    @PostMapping
    public CarritoDetalle guardar(@Valid @RequestBody CarritoDetalle carritoDetalle){
        return carritoDetalleService.crearCarritoDetalle(carritoDetalle);
    }

    @Operation(summary = "Actualizar los detalles de un carrito", description ="Actualiza los detalles de un carrito en base a los datos ingresados")
    @PutMapping("/{id}")
    public CarritoDetalle modificar(@Valid @PathVariable Long id, @RequestBody CarritoDetalle carritoDetalle){
       
            CarritoDetalle carde = carritoDetalleService.buscarPorId(id);
            
            carde.setCantidad(carritoDetalle.getCantidad());
            
            
            carritoDetalleService.crearCarritoDetalle(carde);

            return carde;
            
        
    }

    @Operation(summary = "Borrar los detalles de un carrito", description ="Borra los detalles del carrito cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        carritoDetalleService.eliminarCarritoDetalle(id);
        return ResponseEntity.noContent().build();
    }

}
