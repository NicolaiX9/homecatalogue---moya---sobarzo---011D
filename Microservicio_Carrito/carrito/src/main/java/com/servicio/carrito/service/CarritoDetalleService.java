package com.servicio.carrito.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import com.servicio.carrito.model.CarritoDetalle;
import com.servicio.carrito.repository.CarritoDetalleRepository;


@Service
public class CarritoDetalleService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private CarritoDetalleRepository carritoDetalleRepository;

   

   public CarritoDetalle crearCarritoDetalle(CarritoDetalle carritoDetalle){

        CarritoDetalle guardado = carritoDetalleRepository.save(carritoDetalle);

        CarritoDetalle completo = carritoDetalleRepository.findById(guardado.getId()).orElse(guardado);


        return obtenerDatosProducto(completo);
    }



    public CarritoDetalle buscarPorId(Long id){
        CarritoDetalle carritoDetalle = carritoDetalleRepository.findById(id).orElse(null);
        
        if (carritoDetalle != null){
            //El enriquecer paciente trae los datos en orden 
            return obtenerDatosProducto(carritoDetalle);
        }

        return null;
    
    }

    public List<CarritoDetalle> listar(){
        return carritoDetalleRepository.findAll();
    }

    public void eliminarCarritoDetalle(CarritoDetalle carritoDetalle){
        carritoDetalleRepository.delete(carritoDetalle);
    }

    public CarritoDetalle obtenerDatosProducto(CarritoDetalle carritoDetalle){

        if (carritoDetalle.getIdProducto() != null){
            try{//la variable tipo Object evita que se caiga el programa
                Object producto = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9095/productos/" + carritoDetalle.getIdProducto())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();

                carritoDetalle.setDatosProducto(producto);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    carritoDetalle.setDatosProducto("La información del producto no está disponible");
                }


            
            }

            return carritoDetalle;


    }
}
