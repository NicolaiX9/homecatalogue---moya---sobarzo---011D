package com.servicio.productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.productos.model.Producto;
import com.servicio.productos.model.ProductoCategoria;
import com.servicio.productos.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listar(){
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id){
        return productoRepository.findById(id);
    }

    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }

     public List<Producto> buscarPorCategoria(ProductoCategoria categoria){
        return productoRepository.findByProductoCategoria(categoria);
    }

}
