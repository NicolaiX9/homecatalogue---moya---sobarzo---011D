package com.servicio.transportista.service;

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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.transportista.model.Transportista;
import com.servicio.transportista.repository.TransportistaRepository;

@ExtendWith(MockitoExtension.class)
public class TransportistaTest {

    @Mock
    private TransportistaRepository transportistaRepository;

    @InjectMocks
    private TransportistaService transportistaService;

    @Test
    @DisplayName("Deberia listar todos los transportistas correctamente")
    void listarTransportistaTest(){
        Transportista t = new Transportista();
        t.setId(1L);
        t.setRut("11.214.412-7");

        List<Transportista> listaT = List.of(t);
        when(transportistaRepository.findAll()).thenReturn(listaT);

        List<Transportista> resultado = transportistaService.listarTransportistas();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("11.214.412-7", resultado.get(0).getRut());
        verify(transportistaRepository, times(1)).findAll();
    }
    
    @Test
    @DisplayName("Deberia buscar un transportista por su ID correctamente")
    void buscarPorIdTransportistaTest(){
        Transportista t = new Transportista();
        t.setId(1L);
        t.setRut("12.212.323-7");
        when(transportistaRepository.findByRut("12.212.323-7")).thenReturn(t);
        Transportista resultado = transportistaService.buscarPorRut("12.212.323-7");
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("12.212.323-7", resultado.getRut());
        verify(transportistaRepository, times(1)).findByRut("12.212.323-7");
    }

    @Test
    @DisplayName("Deberia guardar un transportista correctamente")
    void guardarTransportistaTest(){
        Transportista t = new Transportista();
        t.setRut("10.100.200-8");
        when(transportistaRepository.save(any(Transportista.class))).thenAnswer(invocation ->{
            Transportista a = invocation.getArgument(0);
            a.setId(1L);
            return a;
        });
        Transportista resultado = transportistaService.guardarTransportista(t);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("10.100.200-8", resultado.getRut());
        verify(transportistaRepository, times(1)).save(t);
    }



    @Test
    @DisplayName("Deberia eliminar un transportista correctamente")
    void eliminarTransportistaTest(){
        Long id = 1L;
        Mockito.doNothing().when(transportistaRepository).deleteById(id);
        transportistaService.eliminar(id);
        verify(transportistaRepository, times(1)).deleteById(id);
    }



}
