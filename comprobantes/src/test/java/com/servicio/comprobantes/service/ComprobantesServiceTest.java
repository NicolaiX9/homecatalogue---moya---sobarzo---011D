package com.servicio.comprobantes.service;

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

import com.servicio.comprobantes.model.Comprobantes;
import com.servicio.comprobantes.repository.ComprobantesRepository;

@ExtendWith(MockitoExtension.class)
public class ComprobantesServiceTest {

    @Mock
    private ComprobantesRepository comprobantesRepository;
    @InjectMocks
    private ComprobantesService comprobantesService;
    

    // ------- Creacion de metodos de prueba -------

    // metodo de listar
    @Test
    @DisplayName("Deberia listar los comprobantes correctamente")
    void listartodosTest(){
        Comprobantes compro = new Comprobantes();
        compro.setNmroComprobante("1");
        compro.setPdfUrl("https://mi-almacen.com/comprobantes/factura-1.pdf");
        compro.setTotal(10000);
        compro.setIdVenta(1L);
        compro.setFechaEmision(LocalDate.now());
        List<Comprobantes> listaMock = List.of(compro);
        when(comprobantesRepository.findAll()).thenReturn(listaMock);
        List<Comprobantes> resultado = comprobantesService.listar();
        assertNotNull(resultado);
        assertEquals("1", resultado.get(0).getNmroComprobante());
        assertEquals("https://mi-almacen.com/comprobantes/factura-1.pdf", resultado.get(0).getPdfUrl());
        assertEquals(10000, resultado.get(0).getTotal());
        assertEquals(1L, resultado.get(0).getIdVenta());
        assertEquals(LocalDate.now(), resultado.get(0).getFechaEmision());
        verify(comprobantesRepository, times(1)).findAll();
    }
    
    // metodo de crear
    @Test
    @DisplayName("Deberia guardar el comprobantes correctamente")
    // metodo del guardar = save
    void guardarComprobanteTest(){
        Comprobantes comprobantes =  new Comprobantes();
        comprobantes.setNmroComprobante("1");
        comprobantes.setPdfUrl("https://mi-comprobante.com/comprobantes/factura-1.pdf");
        comprobantes.setTotal(10000);
        comprobantes.setIdVenta(1L);
        comprobantes.setFechaEmision(LocalDate.now());
        when(comprobantesRepository.save(any(Comprobantes.class))).thenAnswer(invocation -> {
            Comprobantes a = invocation.getArgument(0);
            a.setId(1L);
            return a;
        });
    
    Comprobantes resultado = comprobantesService.guardar(comprobantes);
    assertNotNull(resultado);
    assertEquals(1L, resultado.getId());
    assertEquals("1", resultado.getNmroComprobante());
    assertEquals("https://mi-comprobante.com/comprobantes/factura-1.pdf", resultado.getPdfUrl());
    assertEquals(10000, resultado.getTotal());
    assertEquals(1L, resultado.getIdVenta());
    assertEquals(LocalDate.now(), resultado.getFechaEmision());
    verify(comprobantesRepository, times(1)).save(comprobantes);
    }

    // metodo de eliminar
    @Test
    @DisplayName("Deberia eliminar el comprobante por su ID correctamente")
    void eliminarAlmacenTest(){
        Long comrpobanteId = 1L;
        doNothing().when(comprobantesRepository).deleteById(comrpobanteId);
        comprobantesService.eliminar(comrpobanteId);
        verify(comprobantesRepository, times(1)).deleteById(comrpobanteId);
    }
}
