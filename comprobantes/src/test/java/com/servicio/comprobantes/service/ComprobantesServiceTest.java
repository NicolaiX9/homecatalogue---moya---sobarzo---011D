package com.servicio.comprobantes.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.servicio.comprobantes.repository.ComprobantesRepository;

@ExtendWith(MockitoExtension.class)
public class ComprobantesServiceTest {

    @Mock
    private ComprobantesRepository comprobantesRepository;
    @InjectMocks
    private ComprobantesService comprobantesService;
    

    // ------- Creacion de metodos de prueba -------

    // metodo de listar

    
    // metodo de crear


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
