package com.servicio.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<Stock> buscarPorId(Long idStock){
        return stockService.buscarPorId(idStock);
    }

    
}
