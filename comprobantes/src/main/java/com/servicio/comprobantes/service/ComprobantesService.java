package com.servicio.comprobantes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.comprobantes.model.Comprobantes;
import com.servicio.comprobantes.repository.ComprobantesRepository;

@Service
public class ComprobantesService {

    @Autowired
    private ComprobantesRepository comprobantesRepository;

    public List<Comprobantes> listar(){
        return comprobantesRepository.findAll();
    }

    public Optional<Comprobantes> buscarPorId(Long id){
        return comprobantesRepository.findById(id);
    }

    public Comprobantes guardar(Comprobantes comprobantes){
        return comprobantesRepository.save(comprobantes);
    }

    public void eliminar(Long id){
        comprobantesRepository.deleteById(id);
    }
}
