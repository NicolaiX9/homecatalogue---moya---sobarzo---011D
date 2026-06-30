package com.servicio.stock.controller;

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

import com.servicio.stock.dto.StockDTO;
import com.servicio.stock.model.Stock;
import com.servicio.stock.service.StockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/stocks")
@Tag(name = "Stock", description = "Operaciones relacionadas con la gestión de stock")
public class StockController {
    
    @Autowired
    private StockService stockService;

    @Operation(summary = "Obtener todos los stocks", description ="Retorna una lista completa de los stocks")
    @GetMapping
    public List<StockDTO> listar(){
        return stockService.listar();
    }

    @Operation(summary = "Obtener un stock mediante su Id", description ="Retorna el stock cuya Id coincide con la ingresada")
    @GetMapping("/{id}")
    public StockDTO buscarPorId(@PathVariable Long id){
        return stockService.buscarPorId(id);
    }

    @Operation(summary = "Crear un stock", description ="Crea un stock en base a los datos ingresados")
    @PostMapping
    public Stock guardar(@Valid @RequestBody Stock stock){
        return stockService.crearStock(stock);
    }

    @Operation(summary = "Actualizar un stock", description ="Actualiza un stock en base a los datos ingresados")
    @PutMapping("/{id}")
    public Stock modificar(@Valid @PathVariable Long id, @RequestBody Stock stock){
       
           //StockDTO sto = stockService.buscarPorId(id);
           Stock stockModificar = new Stock();
           stockModificar.setId(id);
           stockModificar.setCantidad(stock.getCantidad());
           stockModificar.setIdProducto(stock.getIdProducto());
           stockModificar.setIdAlmacen(stock.getIdAlmacen());

           return stockService.crearStock(stockModificar);

            // sto.setCantidad(stock.getCantidad());            
            
            // stockService.crearStock(sto);

            // return sto;
            
        
    }

    @Operation(summary = "Borrar un stock", description ="Borra el stock cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        stockService.eliminarStock(id);
        return ResponseEntity.noContent().build();
    }

}
