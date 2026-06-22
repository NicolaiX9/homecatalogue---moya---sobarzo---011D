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

import com.servicio.transportista.config.ErrorResponse;
import com.servicio.transportista.model.Transportista;
import com.servicio.transportista.service.TransportistaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

//CrossOrigin permite que Swagger lo llame desde cualquier puerto
@CrossOrigin(origins="*") 
@RestController
@RequestMapping("/api/v1/transportistas")
@Tag(name = "Transportistas", description = "Operaciones relacionadas con la gestión de transportistas")
public class TransportistaController {

    
    @Autowired
    private TransportistaService transportistaService;    


    @Operation(summary = "Obtener todos los transportistas", description ="Retorna una lista completa de los transportistas")
    @GetMapping
    public List <Transportista> listar(){
        return transportistaService.listarTransportistas();
    }

    @Operation(summary = "Obtener un transportista mediante su Id", description ="Retorna el transportista cuya Id coincide con la ingresada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
        @ApiResponse(responseCode = "404", description = "No existe el paciente que busca", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity <Transportista> encontrarPorId(@PathVariable Long id) {
        return transportistaService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));
    }
    
    @Operation(summary = "Obtener un transportista mediante su rut", description ="Retorna el transportista cuyo rut coincide con la ingresada")
    @GetMapping("/{rut}")
    public Transportista buscarPorRut(String rut){
        return transportistaService.buscarPorRut(rut);
    }


    @Operation(summary = "Crear un transportista", description ="Crea un transportista en base a los datos ingresados")
    @PostMapping ResponseEntity <Transportista> guardar(@Valid @RequestBody Transportista transportista){
        return ResponseEntity.ok(transportistaService.guardarTransportista(transportista));
    }

    @Operation(summary = "Borrar un transportista", description ="Borra el transportista cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        //El build() llama al constructor
        transportistaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
