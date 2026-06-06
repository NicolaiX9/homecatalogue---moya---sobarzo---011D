package com.servicio.distribuidores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.distribuidores.model.Suministro;
import com.servicio.distribuidores.repository.SuministroRepository;

import jakarta.transaction.Transactional;


@Service
public class SuministroService {
    @Autowired
    private SuministroRepository suministroRepository;

    public List<Suministro> listar(){
        return suministroRepository.findAll();
    }

    public Optional<Suministro> buscarPorId(Long id){
        return suministroRepository.findById(id);
    }

    @Transactional
    public Suministro guardarSuministro(Suministro suministro){
        return suministroRepository.save(suministro);
    }

    public void eliminarSuministro(Long id){
        suministroRepository.deleteById(id);
    }
}
