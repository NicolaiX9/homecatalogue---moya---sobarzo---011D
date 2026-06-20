package com.servicio.despachos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.despachos.model.Despacho;
import com.servicio.despachos.service.DespachoService;

import io.swagger.v3.oas.annotations.media.Schema;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/despachos")
@Schema(description = "Modelo que representa un despacho en el sistema")
public class despachoController {
    
    @Autowired
    private DespachoService despachoService;

    @GetMapping
    public List<Despacho> listar(){
        return despachoService.listarDespachos();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Despacho> crear(@PathVariable Long id){
        return despachoService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }


    // @PutMapping("/{id}")
    // public ResponseEntity<Despacho> modificar (@PathVariable Long id, @RequestBody Despacho despacho ){
    //     try{
    //         ResponseEntity<Despacho> desp = despachoService.buscarPorId(id)
    //         .map(ResponseEntity::ok)
    //         .orElse(ResponseEntity.notFound().build());

    //         desp.getBody().setFechaDesp(despacho.getFechaDesp());
    //         desp.getBody().setCalleDireccion(despacho.getCalleDireccion());
    //         desp.getBody().setNumDireccion(despacho.getNumDireccion());

    //         despachoService.guardarDespacho(desp.getBody());

    //         return desp;

    //     } catch (Exception e){
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        despachoService.eliminar(id);
        return ResponseEntity.notFound().build();
    }

}
