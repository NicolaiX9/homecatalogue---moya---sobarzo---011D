package com.servicio.stock.service;

import java.util.List;

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

   

   public Stock crearStock(Stock stock){

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

        List<Stock> lista = stockRepository.findAll();

        for (Stock stock : lista){
            obtenerDatosAlmacenProducto(stock);
    }

        return stockRepository.findAll();
    }

    public void eliminarStock(Long id){
        stockRepository.deleteById(id);
    }

    public Stock obtenerDatosAlmacenProducto(Stock stock){

        if (stock.getIdProducto() != null){
            try{//la variable tipo Object evita que se caiga el programa
                Object producto = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9094/api/v1/productos/" + stock.getIdProducto())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();

                stock.setDatosProducto(producto);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    stock.setDatosProducto("La información del producto no está disponible");
                }
            }
        if(stock.getIdAlmacen() != null){
            try{//la variable tipo Object evita que se caiga el programa
                Object almacen = webClientBuilder.build()
                .get()
                //el url no es local, el uri sí
                .uri("http://localhost:9092/api/v1/almacenes/" + stock.getIdAlmacen())
                .retrieve()
                //el primer BodyToMono de la lista
                .bodyToMono(Object.class)
                .block();
                
                stock.setDatosAlmacen(almacen);
                } catch(Exception e){

                    //tienes que borrar la e y apretar comillar para que aparezca "datos pacientes"
                    stock.setDatosAlmacen("La información del almacén no está disponible");
                }

                

            
            }

            return stock;


    }

}
