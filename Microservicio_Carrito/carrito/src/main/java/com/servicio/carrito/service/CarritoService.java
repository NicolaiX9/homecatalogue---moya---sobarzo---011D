package com.servicio.carrito.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.carrito.model.Carrito;
import com.servicio.carrito.repository.CarritoRepository;

@Service
public class CarritoService {


    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listar(){
        return carritoRepository.findAll();
    }

   public Carrito crearCarrito(Carrito carrito){

        Carrito guardado = carritoRepository.save(carrito);

        Carrito completo = carritoRepository.findById(guardado.getId()).orElse(guardado);


        return obtenerDatosUsuario(completo);
    }



    public Carrito buscarPorId(Long id){
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        
        if (carrito != null){
            //El enriquecer paciente trae los datos en orden 
            return obtenerDatosUsuario(carrito);
        }

        return null;
    
    }

    public void eliminarCarrito(Carrito carrito){
        carritoRepository.delete(carrito);
    }

    public Carrito obtenerDatosUsuario(Carrito carrito){

        if (carrito.getIdUsuario() != null){
            try{//la variable tipo Object evita que se caiga el programa
                Object usuario = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9091/usuarios/" + carrito.getIdUsuario())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();

                carrito.setDatosUsuario(usuario);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    carrito.setDatosUsuario("La información del usuario no está disponible");
                }


            
            }

            return carrito;


    }

}
