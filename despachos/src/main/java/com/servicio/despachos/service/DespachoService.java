package com.servicio.despachos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.despachos.model.Despacho;
import com.servicio.despachos.repository.DespachoRepository;

import jakarta.transaction.Transactional;

@Service
public class DespachoService {

     @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private DespachoRepository despachoRepository;

    public List<Despacho> listarDespachos(){
       List<Despacho> lista = despachoRepository.findAll();

        for (Despacho despacho : lista){
            obtenerDatosVenta(despacho);
    }

        return despachoRepository.findAll();
    }

    @Transactional
    public Despacho guardarDespacho(Despacho despacho){
        Despacho guardado  = despachoRepository.save(despacho);

        Despacho completo = despachoRepository.findById(guardado.getId()).orElse(guardado);


        return obtenerDatosVenta(completo);
    }

    public Despacho buscarPorId(Long id){
       Despacho despacho = despachoRepository.findById(id).orElse(null);
        
        if (despacho != null){
            //El enriquecer paciente trae los datos en orden 
            return obtenerDatosVenta(despacho);
        }

        return null;
    
    }
    
    public void eliminar(Long id){
        despachoRepository.deleteById(id);
    }


    public Despacho obtenerDatosVenta(Despacho despacho){

        if (despacho.getIdVenta() != null){
            try{//la variable tipo Object evita que se caiga el programa
                Object venta = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9094/api/v1/productos/" + despacho.getIdVenta())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();

                despacho.setDatosVenta(venta);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    despacho.setDatosVenta("La información de la venta no está disponible");
                }
            


    }
    return despacho;
}
}
