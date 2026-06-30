package com.servicio.despachos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.servicio.despachos.model.Despacho;
import com.servicio.despachos.repository.DespachoRepository;

@ExtendWith(MockitoExtension.class)
public class DespachosTest {

    @Mock
    private DespachoRepository despachoRepository;
    @InjectMocks
    private DespachoService despachoService;

    @Test
    @DisplayName("Deberia listar los despachos correctamente")
    void listarDespachosTest(){
        Despacho desp = new Despacho();
        desp.setId(1L);
        desp.setFechaDesp(LocalDate.now());
        desp.setCalleDireccion("Camino Melipilla");
        desp.setNumDireccion("1022");
        desp.setIdVenta(1L);
        List<Despacho> listaDesp = List.of(desp);
        when(despachoRepository.findAll()).thenReturn(listaDesp);
        List<Despacho> resultado = despachoService.listarDespachos();
        assertNotNull(resultado);
        assertEquals(1L, resultado.get(0).getId());
        assertEquals(LocalDate.now(), resultado.get(0).getFechaDesp());
        assertEquals("Camino Melipilla", resultado.get(0).getCalleDireccion());
        assertEquals("1022", resultado.get(0).getNumDireccion());
        assertEquals(1L, resultado.get(0).getIdVenta());
        verify(despachoRepository, times(2)).findAll();
    }

    @Test
    @DisplayName("Deberia guardar el despacho correctamemte")
    void guardarDespachoTest(){
        Despacho despacho =  new Despacho();
        despacho.setFechaDesp(LocalDate.now());
        despacho.setCalleDireccion("Silva Carvallo");
        despacho.setNumDireccion("1122");
        despacho.setIdVenta(1L);
        when(despachoRepository.save(any(Despacho.class))).thenAnswer(invocation -> {
            Despacho a = invocation.getArgument(0);
            a.setId(1L);
            return a;
        });
    
    Despacho resultado = despachoService.guardarDespacho(despacho);
    assertNotNull(resultado);
    assertEquals(1L, resultado.getId());
    assertEquals(LocalDate.now(), resultado.getFechaDesp());
    assertEquals("Silva Carvallo", resultado.getCalleDireccion());
    assertEquals("1122", resultado.getNumDireccion());
    assertEquals(1L, resultado.getIdVenta());
    verify(despachoRepository, times(1)).save(despacho);
    }

    @Test
    @DisplayName("Deberia eliminar el despacho correctamente")
    void eliminarDespachoTest(){
        Long id = 1L;
        doNothing().when(despachoRepository).deleteById(id);
        despachoService.eliminar(id);
        verify(despachoRepository, times(1)).deleteById(id);
    

    }
}
