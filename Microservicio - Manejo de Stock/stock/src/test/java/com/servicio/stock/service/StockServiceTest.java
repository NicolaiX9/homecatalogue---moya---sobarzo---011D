package com.servicio.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.stock.model.Stock;
import com.servicio.stock.repository.StockRepository;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;
    
    @Mock
    private WebClient.Builder webClientbBuilder;

    @InjectMocks
    private StockService stockService;

    // Metodos: 
// Listar,
// BuscarPorId,
// Guardar,
// Actualizar,
// Eliminar.


    @Test
    @DisplayName("Deberia listar el despacho correctamente")
    void listarStockTest(){
        Stock stock = new Stock(); 
        stock.setCantidad(50);
        stock.setIdProducto(1L);
        stock.setIdAlmacen(1L);
        stock.setDatosAlmacen(stock.getDatosAlmacen());
        stock.setDatosProducto(stock.getDatosProducto());
        List<Stock> listaStock = List.of(stock);
        when(stockRepository.findAll()).thenReturn(listaStock);
        List<Stock> resultado = stockService.listar();
        assertNotNull(resultado);
        assertEquals(50, resultado.get(0).getCantidad());
        assertEquals(1L, resultado.get(0).getIdProducto());
        assertEquals(1L, resultado.get(0).getIdAlmacen());
        assertEquals(stock, resultado.get(0).getDatosAlmacen());
        assertEquals(stock, resultado.get(0).getDatosProducto());
        verify(stockRepository, times(1)).findAll();
    }


    @Test
    @DisplayName("Deberia guardar el stock correctamente")
    void guardarStockTest(){
        Stock stock = new Stock();
        stock.setCantidad(50);
        stock.setIdProducto(1L);
        stock.setIdAlmacen(1L);
        stock.setDatosAlmacen(stock.getDatosAlmacen());
        stock.setDatosProducto(stock.getDatosProducto());
        when(stockRepository.save(any(Stock.class))).thenAnswer(invocation ->{
            Stock a = new Stock();
            a.setId(1L);
            return a;
        });
        Stock resultado = new Stock();
        assertNotNull(resultado);
        assertEquals(10, resultado.getCantidad());
        assertEquals(1L, resultado.getIdProducto());
        assertEquals(1L, resultado.getIdAlmacen());
        assertEquals(1L, resultado.getDatosAlmacen());
        assertEquals(1L, resultado.getDatosProducto());
        verify(stockRepository, times(1)).save(stock);
    }
}
