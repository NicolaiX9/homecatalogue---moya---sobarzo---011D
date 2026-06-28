package com.servicio.distribuidores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.distribuidores.model.Distribuidor;
import com.servicio.distribuidores.repository.DistribuidorRepository;

import jakarta.transaction.Transactional;

@Service
public class DistribuidorService {

    @Autowired
    private DistribuidorRepository distribuidorRepository;

    public List<Distribuidor> listar(){
        return distribuidorRepository.findAll();
    }

    public Optional<Distribuidor> buscarPorId(Long id){
        return distribuidorRepository.findById(id);
    }

    @Transactional
    public Distribuidor guardarDistribuidor(Distribuidor distribuidor){
        return distribuidorRepository.save(distribuidor);
    }

    public void eliminarDistribuidor(Long id){
        distribuidorRepository.deleteById(id);
    }
}
