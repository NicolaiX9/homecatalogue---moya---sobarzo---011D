package com.servicio.ventas.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.ventas.model.Venta;
import com.servicio.ventas.repository.VentaRepository;
import com.servicio.ventas.service.VentaService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class VentaTest {

    @Mock
    private VentaRepository ventaRepository;

    @Mock 
    private WebClient.Builder webClientbBuilder;

    @InjectMocks
    private VentaService ventaService;

    @Test
    @DisplayName("Deberia guardar una venta correctamente")
    void crearVentaTest(){
        Venta venta = new Venta();
        venta.setIdCarrito(1L);
        
        when(ventaRepository.save(any(Venta.class))).thenAnswer(invocation ->{
            Venta a = invocation.getArgument(0);
            a.setId(1L);
            return a;
        });
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));
        Venta resultado = ventaService.crearVenta(venta);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertNotNull(resultado.getDatosCarrito());
        verify(ventaRepository, times(1)).save(venta);
    }

    @Test
    @DisplayName("Deberia buscar una venta por ID correctamente ")
    void buscarPorIdTest(){
        Venta venta = new Venta();
        venta.setId(1L);
        venta.setIdCarrito(1L);
        
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));

        WebClient webClient  = Mockito.mock(WebClient.class);
        WebClient.RequestHeadersUriSpec uriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec headersSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
        
        Object carrito = new Object();

        when(webClientbBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(carrito));

        Venta resultado = ventaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertNotNull(resultado.getDatosCarrito());
        verify(ventaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deberia listar todas las venta correctamente ")
    void listarVentasTest(){
        Venta venta = new Venta();
        venta.setId(1L);
        venta.setIdCarrito(1L);
        List<Venta> listaVentas = List.of(venta);

        when(ventaRepository.findAll()).thenReturn(listaVentas);

        List<Venta> resultado = ventaService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(ventaRepository, times(2)).findAll();
    }

    @Test
    @DisplayName("Deberia eliminar una venta por ID correctamente")
    void eliminarVentaTest(){
        Long id = 1L;
        Mockito.doNothing().when(ventaRepository).deleteById(id);

        ventaService.eliminarVenta(id);

        verify(ventaRepository, times(1)).deleteById(id);
    }













}


