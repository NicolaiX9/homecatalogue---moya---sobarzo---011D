package com.servicio.almacenes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.almacenes.model.Almacen;
import com.servicio.almacenes.repository.AlmacenRepository;

@Service
public class AlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    public List<Almacen> listar(){
        return almacenRepository.findAll();
    }

    public Optional <Almacen> buscarPorId(Long id){
        return almacenRepository.findById(id);
    }

    public Almacen crearAlmacen(Almacen almacen){
        return almacenRepository.save(almacen);
    }

    public void eliminarAlmacen(Long id){
        almacenRepository.deleteById(id);
    }
}
