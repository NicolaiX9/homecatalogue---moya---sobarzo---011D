package com.servicio.almacenes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
    void buscarPorIdTest(){
        Long id = 1L;
        Almacen alm = new Almacen();
        alm.setId(id);
        alm.setCalleDireccion("Av. Pajaritos");
        alm.setNumeroDireccion("1234");
        when(almacenRepository.findById(id)).thenReturn(Optional.of(alm));
        Optional <Almacen> resultadoOptional = almacenService.buscarPorId(id);
        assertTrue(resultadoOptional.isPresent());
        Almacen resultado = resultadoOptional.get();
        
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Av. Pajaritos", resultado.getCalleDireccion());
        assertEquals("1234", resultado.getNumeroDireccion());
        verify(almacenRepository, times(1)).findById(id);
    }
    
    @Test
    @DisplayName("Deberia listar los almacenes correctamente")
    void listartodosTest(){
        Almacen alma = new Almacen();
        alma.setCalleDireccion("Gran Avenida");
        alma.setNumeroDireccion("5566");
        List<Almacen> listaMock = List.of(alma);
        when(almacenRepository.findAll()).thenReturn(listaMock);
        List<Almacen> resultado = almacenService.listar();
        assertNotNull(resultado);
        assertEquals("Gran Avenida", resultado.get(0).getCalleDireccion());
        assertEquals("5566", resultado.get(0).getNumeroDireccion());
        verify(almacenRepository, times(1)).findAll();
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

    // // metodo de actualizar
    // void actualizarAlmacenTest(){
    //     Long id = 1L;
    //     Almacen almaActu = new Almacen();
    //     almaActu.setId(id);
    //     almaActu.setCalleDireccion("Calle Vieja");
    //     almaActu.setNumeroDireccion("2222");

    //     Almacen datosNuevos = new Almacen();
    //     datosNuevos.setCalleDireccion("Calle Nueva");
    //     datosNuevos.setNumeroDireccion("2222");
        
    //     // buscamos si existe
    //     when(almacenRepository.findById(id)).thenReturn(Optional.of(almaActu));
    //     // guardar cambios y retornar lo q esta guardando
    //     when(almacenRepository.save(any(Almacen.class))).thenAnswer(invocation -> {
    //         Almacen a = invocation.getArgument(0);
    //         return a;
    //     });
    //     Almacen resultado = almacenService.ac

    //     // verificacion
    //     assertNotNull(resultado);
    //     assertEquals("Calle Nueva", resultado.getCalleDireccion());
    //     assertEquals("2222", resultado.getNumeroDireccion());
    //     verify(almacenRepository, times(1)).findById(id);
    //     verify(almacenRepository, times(1)).save(any(Almacen.class));
    // }




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
