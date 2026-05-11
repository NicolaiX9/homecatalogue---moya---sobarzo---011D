package com.servicio.stock.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.stock.model.Stock;
import java.util.List;


public interface StockRepository extends JpaRepository<Stock, Long>{

    List<Stock> findByIdStock(Long idStock);
}
