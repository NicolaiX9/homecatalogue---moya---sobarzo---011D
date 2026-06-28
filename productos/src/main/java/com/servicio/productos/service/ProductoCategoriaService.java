package com.servicio.productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.productos.model.ProductoCategoria;
import com.servicio.productos.repository.ProductoCategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoCategoriaService {

    // listar, guardar, actualizar, eliminar
    @Autowired
    private ProductoCategoriaRepository productoCategoriaRepository;

    public List<ProductoCategoria> listar(){
        return productoCategoriaRepository.findAll();
    }

    @Transactional
    public ProductoCategoria guardar(ProductoCategoria productoCategoria){
        return productoCategoriaRepository.save(productoCategoria);
    }

    public void eliminar(Long id){
        productoCategoriaRepository.deleteById(id);
    }



}
