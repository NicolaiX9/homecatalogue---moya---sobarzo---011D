package com.servicio.carrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.carrito.model.Carrito;
import com.servicio.carrito.repository.CarritoRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listar(){
        return carritoRepository.findAll();
    }

   public Carrito crearCarrito(Carrito producto){
        return carritoRepository.save(producto);
    }

    public Optional<Carrito> buscarPorId(Long id){
        return carritoRepository.findById(id);
    }

    public void eliminarCarrito(Carrito carrito){
        carritoRepository.delete(carrito);
    }

}
