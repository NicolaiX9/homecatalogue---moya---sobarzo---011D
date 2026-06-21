package com.servicio.transportista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.transportista.model.Transportista;
import com.servicio.transportista.service.TransportistaService;


import io.swagger.v3.oas.annotations.tags.Tag;

//CrossOrigin permite que Swagger lo llame desde cualquier puerto
@CrossOrigin(origins="*") 
@RestController
@RequestMapping("/api/v1/transportistas")
@Tag(name = "Transportistas", description = "Operaciones relacionadas con la gestión de transportistas")
public class TransportistaController {

    
    @Autowired
    private TransportistaService transportistaService;    


    @GetMapping
    public List <Transportista> listar(){
        return transportistaService.listarTransportistas();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Transportista> encontrarPorId(@PathVariable Long id) {
        return transportistaService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{rut}")
    public Transportista buscarPorRut(String rut){
        return transportistaService.buscarPorRut(rut);
    }



    @PostMapping ResponseEntity <Transportista> guardar(@RequestBody Transportista transportista){
        return ResponseEntity.ok(transportistaService.guardarTransportista(transportista));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        //El build() llama al constructor
        return ResponseEntity.noContent().build();
    }

}
