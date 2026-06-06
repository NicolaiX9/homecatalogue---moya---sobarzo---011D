package com.servicio.comprobantes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.comprobantes.model.Comprobantes;
import com.servicio.comprobantes.service.ComprobantesService;

@RestController
@RequestMapping("/api/v1/comprobantes")
public class ComprobantesController {

    @Autowired
    private ComprobantesService comprobantesService;

    @GetMapping
    public List<Comprobantes> listar(){
        return comprobantesService.listar();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Comprobantes> crear(@PathVariable Long id){
        return comprobantesService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id ){
        comprobantesService.eliminar(id);
        return ResponseEntity.notFound().build();
    }
}
