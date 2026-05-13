package com.servicio.stock.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> listar(){
        return stockService.listarStocks();
    }

    @PostMapping
    public Stock crear(Stock stock){
        return stockService.crearStock(stock);
    }

    @GetMapping("/{idStock}")
    public Optional<Stock> buscarPorId(Long idStock){
        return stockService.buscarPorId(idStock);
    }

    @PutMapping("/{idStock}")
    public ResponseEntity<Stock> actualizar(@PathVariable Long idStock, @RequestBody Stock stock){
        try {
            ResponseEntity<Stock> sto = stockService.buscarPorId(idStock)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

            sto.getBody().setCantidad(stock.getCantidad());

            stockService.crearStock(stock);

            return ResponseEntity.ok(stock);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
}

    @DeleteMapping("/{idStock}")
    public void eliminar(Stock stock){
        stockService.borrarStock(stock);
    }
}
