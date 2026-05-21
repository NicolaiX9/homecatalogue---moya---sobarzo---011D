package com.servicio.transportista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.transportista.model.Transportista;
import com.servicio.transportista.repository.TransportistaRepository;

import jakarta.transaction.Transactional;

@Service
public class TransportistaService {

    @Autowired
    private TransportistaRepository transportistaRepository;

    public List <Transportista> listarTransportistas(){
        return transportistaRepository.findAll();
    }

    public Optional <Transportista> buscarPorId(Long id){
        return transportistaRepository.findById(id);
    }

    public Transportista buscarPorRut(String rut){
        return transportistaRepository.findByRut(rut);
    }

    @Transactional
    public Transportista guardarTransportista(Transportista transportista){
        return transportistaRepository.save(transportista);
    }

    public void eliminar(Long id){
      transportistaRepository.deleteById(id);
    
    }


}
