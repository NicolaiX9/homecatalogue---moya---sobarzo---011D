package com.servicio.almacenes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.almacenes.model.Almacen;
import com.servicio.almacenes.service.AlmacenService;

@RestController
@RequestMapping("/api/v1/almacenes")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    @GetMapping
    public List<Almacen> listar(){
        return almacenService.listar();
    }

    @PostMapping
    public Almacen guardar(@RequestBody Almacen almacen){
        return almacenService.crearAlmacen(almacen);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        almacenService.eliminarAlmacen(id);
        return ResponseEntity.noContent().build();
    }
}
