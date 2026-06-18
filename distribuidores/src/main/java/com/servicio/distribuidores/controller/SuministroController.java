package com.servicio.distribuidores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.distribuidores.model.Suministro;
import com.servicio.distribuidores.service.SuministroService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/distribuidor/suministro")
public class SuministroController {
    @Autowired
    private SuministroService suministroService;

    @GetMapping
    public List<Suministro> listar(){
        return suministroService.listar();
    }

    @GetMapping("/{id}")
    public Suministro buscarPorId(@PathVariable Long id){
        return suministroService.buscarPorId(id);
    }

    @PostMapping
    public Suministro guardar(@RequestBody Suministro suministro){
        return suministroService.crearSuministro(suministro);
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Suministro> modificar(@PathVariable Long id, @RequestBody Suministro suministro){
    //     try{
    //         ResponseEntity<Suministro> sumi = suministroService.buscarPorId(id)
    //         .map(ResponseEntity::ok)
    //         .orElse(ResponseEntity.notFound().build());

    //         sumi.getBody().setCosto(suministro.getCosto());
    //         sumi.getBody().setCantidad(suministro.getCantidad());

    //         suministroService.guardarSuministro(sumi.getBody());

    //         return sumi;
    //     } catch(Exception e) {
    //         return ResponseEntity.notFound().build();
    //     }

    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        suministroService.eliminarSuministro(id);
        return ResponseEntity.notFound().build();
    }
}
