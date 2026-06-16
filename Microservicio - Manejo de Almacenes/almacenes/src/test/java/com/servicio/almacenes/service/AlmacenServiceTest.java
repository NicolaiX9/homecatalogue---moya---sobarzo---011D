package com.servicio.almacenes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.servicio.almacenes.model.Almacen;
import com.servicio.almacenes.repository.AlmacenRepository;

@ExtendWith(MockitoExtension.class) // usamos mockito para simular objetos
public class AlmacenServiceTest {
    @Mock
    private AlmacenRepository almacenRepository;
    @InjectMocks
    private AlmacenService almacenService;
    
    
    // ------- Creacion de metodos de prueba -------

    // metodo de buscar por id
    @Test
    @DisplayName("Deberia buscar un almacen por ID correctamente")
    void buscarPorId(){
        Long id = 1L;
        Almacen almock = new Almacen();
        almock.setId(id);
        when(almacenRepository).findById(id).thenReturn(Optional.of(almock));
        Almacen resultado = almacenService.buscarPorId(id);
        verify(almacenRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deberia guardar el almacen correctamente")
    // metodo del guardar = save
    void guardarAlmacenTest(){
        Almacen almacen =  new Almacen();
        almacen.setCalleDireccion("Pajaritos");
        almacen.setNumeroDireccion("7845");
        when(almacenRepository.save(any(Almacen.class))).thenAnswer(invocation -> {
            Almacen a = invocation.getArgument(0);
            a.setId(1L);
            return a;
        });
    
    Almacen resultado = almacenService.crearAlmacen(almacen);
    assertNotNull(resultado);
    assertEquals(1L, resultado.getId());
    assertEquals("Pajaritos", resultado.getCalleDireccion());
    verify(almacenRepository, times(1)).save(almacen);
    }

    // metodo del eliminar = delete
    @Test
    @DisplayName("Deberia eliminar el almacen por su ID correctamente")
    void eliminarAlmacenTest(){
        Long almacenId = 1L;
        doNothing().when(almacenRepository).deleteById(almacenId);
        almacenService.eliminarAlmacen(almacenId);
        verify(almacenRepository, times(1)).deleteById(almacenId);
    }






}
