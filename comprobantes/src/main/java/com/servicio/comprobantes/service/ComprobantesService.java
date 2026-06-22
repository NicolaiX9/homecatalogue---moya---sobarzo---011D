package com.servicio.comprobantes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.comprobantes.model.Comprobantes;
import com.servicio.comprobantes.repository.ComprobantesRepository;

@Service
public class ComprobantesService {

     @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ComprobantesRepository comprobantesRepository;

    public List<Comprobantes> listar(){
        List<Comprobantes> lista = comprobantesRepository.findAll();

        for (Comprobantes comprobantes : lista){
            obtenerDatosVenta(comprobantes);
    }

        return comprobantesRepository.findAll();
    }

    public Comprobantes buscarPorId(Long id){
         Comprobantes comprobantes = comprobantesRepository.findById(id).orElse(null);
        
        if (comprobantes != null){
            //El enriquecer paciente trae los datos en orden 
            return obtenerDatosVenta(comprobantes);
        }

        return null;
    
    }

    public Comprobantes guardar(Comprobantes comprobantes){
        Comprobantes guardado = comprobantesRepository.save(comprobantes);

        Comprobantes completo = comprobantesRepository.findById(guardado.getId()).orElse(guardado);


        return obtenerDatosVenta(completo);
    }

    public void eliminar(Long id){
        comprobantesRepository.deleteById(id);
    }

    public Comprobantes obtenerDatosVenta(Comprobantes comprobantes){

        if (comprobantes.getIdVenta() != null){
            try{//la variable tipo Object evita que se caiga el programa
                Object venta = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9094/api/v1/productos/" + comprobantes.getIdVenta())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();

                comprobantes.setDatosVenta(venta);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    comprobantes.setDatosVenta("La información de la venta no está disponible");
                }
            


    }
    return comprobantes;
}

}



