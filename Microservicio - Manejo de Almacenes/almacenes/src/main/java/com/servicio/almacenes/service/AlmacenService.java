package com.servicio.almacenes.service;

import java.util.List;

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

    public Almacen crearAlmacen(Almacen almacen){
        return almacenRepository.save(almacen);
    }

    public void borrarAlmacen(Almacen almacen){
        almacenRepository.delete(almacen);
    }
}
