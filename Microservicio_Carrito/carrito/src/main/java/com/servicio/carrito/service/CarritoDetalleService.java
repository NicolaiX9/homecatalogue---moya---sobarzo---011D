package com.servicio.carrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.carrito.model.CarritoDetalle;
import com.servicio.carrito.repository.CarritoDetalleRepository;

@Service
public class CarritoDetalleService {

    @Autowired
    private CarritoDetalleRepository carritoDetalleRepository;

    public List<CarritoDetalle> listar(){
        return carritoDetalleRepository.findAll();
    }

   public CarritoDetalle crearCarritoDetalle(CarritoDetalle carritoDetalle){
        return carritoDetalleRepository.save(carritoDetalle);
    }

    public Optional<CarritoDetalle> buscarPorId(Long id){
        return carritoDetalleRepository.findById(id);
    }

    public void eliminarCarrito(CarritoDetalle carritoDetalle){
        carritoDetalleRepository.delete(carritoDetalle);
    }
}
