package com.servicio.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        return stockService.listar();
    }

    @GetMapping("/{id}")
    public Stock buscarPorId(@PathVariable Long id){
        return stockService.buscarPorId(id);
    }

    @PostMapping
    public Stock crearCarrito(@RequestBody Stock stock){
        return stockService.crearStock(stock);
    }

    @PutMapping("/{id}")
    public Stock actualizar(@PathVariable Long id, @RequestBody Stock stock){
       
           Stock sto = stockService.buscarPorId(id);
            
            sto.setCantidad(stock.getCantidad());
            sto.setIdProducto(stock.getIdProducto());
            sto.setIdAlmacen(stock.getIdAlmacen());
            
            
            
            stockService.crearStock(sto);

            return sto;
            
        
    }


    @DeleteMapping("/{id}")
    public void eliminarCarrito(Stock stock){
        stockService.eliminarStock(stock);
    }

}
