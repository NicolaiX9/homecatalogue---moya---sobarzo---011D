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

import com.servicio.stock.model.Stock;
import com.servicio.stock.service.StockService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    
    @Autowired
    private StockService stockService;


    @GetMapping
    public List<Stock> listar(){
        return stockService.listar();
    }

    @GetMapping("/{id}")
    public Stock buscarPorId(@PathVariable Long id){
        return stockService.buscarPorId(id);
    }

    @PostMapping
    public Stock guardar(@RequestBody Stock stock){
        return stockService.crearStock(stock);
    }

    @PutMapping("/{id}")
    public Stock modificar(@PathVariable Long id, @RequestBody Stock stock){
       
           Stock sto = stockService.buscarPorId(id);
            
            sto.setCantidad(stock.getCantidad());            
            
            stockService.crearStock(sto);

            return sto;
            
        
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        stockService.eliminarStock(id);
        return ResponseEntity.noContent().build();
    }

}
