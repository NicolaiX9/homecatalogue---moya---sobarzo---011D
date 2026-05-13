package com.servicio.stock.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.stock.model.Stock;


public interface StockRepository extends JpaRepository<Stock, Long>{

}
