package com.servicio.distribuidores.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.distribuidores.model.Suministro;
import com.servicio.distribuidores.repository.SuministroRepository;

import jakarta.transaction.Transactional;


@Service
public class SuministroService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private SuministroRepository suministroRepository;

    public Suministro buscarPorId(Long id){
        Suministro suministro = suministroRepository.findById(id).orElse(null);
        
        if (suministro != null){
            //El enriquecer paciente trae los datos en orden 
            return obtenerDatosProducto(suministro);
        }

        return null;
    
    }

    public List<Suministro> listar(){

        List<Suministro> lista = suministroRepository.findAll();

        for (Suministro suministro : lista){
            obtenerDatosProducto(suministro);
    }

        return suministroRepository.findAll();
    }

    @Transactional
    public Suministro crearSuministro(Suministro suministro){
        Suministro guardado = suministroRepository.save(suministro);

        Suministro completo = suministroRepository.findById(guardado.getId()).orElse(guardado);


        return obtenerDatosProducto(completo);
    }

    public void eliminarSuministro(Long id){
        suministroRepository.deleteById(id);
    }

    public Suministro obtenerDatosProducto(Suministro suministro){

        if (suministro.getIdProducto() != null){
            try{//la variable tipo Object evita que se caiga el programa
                Object producto = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9094/api/v1/productos/" + suministro.getIdProducto())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();

                suministro.setDatosProducto(producto);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    suministro.setDatosProducto("La información del producto no está disponible");
                }
            }
            return suministro;
        }





    }
