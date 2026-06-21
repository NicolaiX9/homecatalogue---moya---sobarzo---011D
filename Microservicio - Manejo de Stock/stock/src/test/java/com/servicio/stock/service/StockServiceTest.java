package com.servicio.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.stock.dto.AlmacenDTO;
import com.servicio.stock.dto.CategoriaDTO;
import com.servicio.stock.dto.ProductoDTO;
import com.servicio.stock.dto.StockDTO;
import com.servicio.stock.model.Stock;
import com.servicio.stock.repository.StockRepository;

import reactor.core.publisher.Mono;

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
        stock.setId(1L);
        stock.setCantidad(50);
        stock.setIdProducto(1L);
        stock.setIdAlmacen(1L);
       
        List<Stock> listaStock = List.of(stock);

        when(stockRepository.findAll()).thenReturn(listaStock);

        AlmacenDTO almacenDTO = new AlmacenDTO(1L, "Av Pajaritos", "1122");
        CategoriaDTO categoriaDTO = new CategoriaDTO(1L, "Hogar");
        ProductoDTO productoDTO = new ProductoDTO(1L, "Estante Negro", "180X90X60", 21000, categoriaDTO );


        WebClient webClient  = Mockito.mock(WebClient.class);
        WebClient.RequestHeadersUriSpec uriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec headersSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
        
        when(webClientbBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        
        when(responseSpec.bodyToMono(any(Class.class)))
                .thenReturn(Mono.just(productoDTO))
                .thenReturn(Mono.just(almacenDTO));

        
        List<StockDTO> resultado = stockService.listar();

        assertNotNull(resultado, "La lista de stock no debe ser nula");
        assertEquals(1, resultado.size(), "Deberia retornar examente un elemento");
        
        StockDTO dtoResultados = resultado.get(0);
        assertEquals(1L, dtoResultados.getId());
        assertEquals(50,dtoResultados.getCantidad());
        assertEquals(1L,dtoResultados.getIdProducto());
        assertEquals(1L,dtoResultados.getIdAlmacen());

        assertNotNull(dtoResultados.getDatosAlmacen(), "Los datos del almacen no deben ser nulos");
        assertEquals("Av Pajaritos", dtoResultados.getDatosAlmacen().getCalleDireccion());

        assertNotNull(dtoResultados.getDatosProducto(), "Los datos del producto no deben ser nulos");
        assertEquals("Estante Negro", dtoResultados.getDatosProducto().getNombre());
        assertEquals("Hogar", dtoResultados.getDatosProducto().getProductoCategoria().getCategoria());

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
            Stock a = invocation.getArgument(0);
            a.setId(1L);
            return a;
        });
        Stock resultado = stockService.crearStock(stock);
        assertNotNull(resultado);
        assertEquals(50, resultado.getCantidad());
        assertEquals(1L, resultado.getIdProducto());
        assertEquals(1L, resultado.getIdAlmacen());
        assertNotNull(resultado.getDatosAlmacen());
        assertNotNull(resultado.getDatosProducto());
        verify(stockRepository, times(1)).save(stock);
    }















    @Test
    @DisplayName("Deberia eliminar un stock por ID correctamente")
    void eliminarStockTest(){
        Long id = 1L;
        Mockito.doNothing().when(stockRepository).deleteById(id);
        stockService.eliminarStock(id);
        verify(stockRepository, times(1)).deleteById(id);
    }










}
