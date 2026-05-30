package com.servicio.ventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.ventas.model.Venta;
import com.servicio.ventas.repository.VentaRepository;


@Service
public class VentaService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private VentaRepository ventaRepository;

   

   public Venta crearVenta(Venta venta){

        Venta guardado = ventaRepository.save(venta);

        Venta completo = ventaRepository.findById(guardado.getId()).orElse(guardado);


        return obtenerDatosCarrito(completo);
    }



    public Venta buscarPorId(Long id){
        Venta venta = ventaRepository.findById(id).orElse(null);
        
        if (venta != null){
            //El enriquecer paciente trae los datos en orden 
            return obtenerDatosCarrito(venta);
        }

        return null;
    
    }

    public List<Venta> listar(){

        List<Venta> lista = ventaRepository.findAll();

        for (Venta venta : lista){
            obtenerDatosCarrito(venta);
    }

        return ventaRepository.findAll();
    }

    public void eliminarVenta(Long id){
        ventaRepository.deleteById(id);
    }

    public Venta obtenerDatosCarrito(Venta venta){

        if (venta.getIdCarrito() != null){
            try{//la variable tipo Object evita que se caiga el programa
                Object producto = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9095/api/v1/carritos/" + venta.getIdCarrito())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();

                venta.setDatosCarrito(venta);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    venta.setDatosCarrito("La información del carrito no está disponible");
                }
            }
    

            return venta;


    }

}
