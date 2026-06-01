package com.servicio.despachos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.despachos.model.Despacho;
import com.servicio.despachos.repository.DespachoRepository;

import jakarta.transaction.Transactional;

@Service
public class DespachoService {

    @Autowired
    private DespachoRepository despachoRepository; 

    public List<Despacho> listar(){
        return despachoRepository.findAll();
    }

    public Optional<Despacho> buscarPorId(Long id){
        return despachoRepository.findById(id);
    }

    @Transactional
    public Despacho guardar(Despacho despacho){
        return despachoRepository.save(despacho);
    }

    public void eliminar(Long id){
        despachoRepository.deleteById(id);
    }
}
