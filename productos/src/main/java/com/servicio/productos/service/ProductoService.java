package com.servicio.productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicio.productos.model.Producto;
import com.servicio.productos.model.ProductoCategoria;
import com.servicio.productos.repository.ProductoCategoriaRepository;
import com.servicio.productos.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoCategoriaRepository productoCategoriaRepository;

    public List<Producto> listar(){
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id){
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto crearProducto(Producto producto){
        ProductoCategoria cate = productoCategoriaRepository.findById(
            //Busca el id y, si no existe, lanza un mensaje informándolo 
            producto.getProductoCategoria().getId())
        .orElseThrow(() -> new RuntimeException("Tipo de usuario no encontrado"));

        producto.setProductoCategoria(cate);

    return productoRepository.save(producto);
}

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }

     public List<Producto> buscarPorCategoria(Long id){
        return productoRepository.findByProductoCategoriaId(id);
    }

}
