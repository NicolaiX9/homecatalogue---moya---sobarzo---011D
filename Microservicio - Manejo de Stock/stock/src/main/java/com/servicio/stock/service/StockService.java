package com.servicio.stock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.servicio.stock.model.Stock;
import com.servicio.stock.repository.StockRepository;

@Service
public class StockService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private StockRepository stockRepository;

   

   public Stock crearCarrito(Stock stock){

        Stock guardado = stockRepository.save(stock);

        Stock completo = stockRepository.findById(guardado.getId()).orElse(guardado);


        return obtenerDatosAlmacenProducto(completo);
    }



    public Stock buscarPorId(Long id){
        Stock stock = stockRepository.findById(id).orElse(null);
        
        if (stock != null){
            //El enriquecer paciente trae los datos en orden 
            return obtenerDatosAlmacenProducto(stock);
        }

        return null;
    
    }

    public List<Stock> listar(){
        return stockRepository.findAll();
    }

    public void eliminarCarrito(Stock stock){
        stockRepository.delete(stock);
    }

    public Stock obtenerDatosAlmacenProducto(Stock stock){

        if (stock.getId() != null){
            try{//la variable tipo Object evita que se caiga el programa
                Object Almacen = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9092/almacenes/" + stock.g())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();

                stock.setDatosAlmacen(Almacen);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    stock.setDatosUsuario("La información del usuario no está disponible");
                }

                try{//la variable tipo Object evita que se caiga el programa
                Object Almacen = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9092/almacenes/" + stock.g())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();

                stock.setDatosUsuario(stock);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    carrito.setDatosUsuario("La información del usuario no está disponible");
                }


            
            }

            return carrito;


    }

}
