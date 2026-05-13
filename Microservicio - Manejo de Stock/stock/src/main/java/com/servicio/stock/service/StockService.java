package com.servicio.stock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.stock.model.Stock;
import com.servicio.stock.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    // listar
    public List<Stock> listarStocks(){
        return stockRepository.findAll();
    }

    // Crear
    public Stock crearStock(Stock stock){
        return stockRepository.save(stock);
    } 
    // Buscar por id
    public Optional<Stock> buscarPorId(Long idStock){
        return stockRepository.findById(idStock);
    } 
    // Eliminar
    public void borrarStock(Stock stock){
        stockRepository.delete(stock);
    }

}
