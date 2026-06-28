package com.servicio.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.productos.config.ErrorResponse;
import com.servicio.productos.model.Producto;
import com.servicio.productos.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con la gestión de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Obtener todos los productos", description ="Retorna una lista completa de los productos")
    @GetMapping
    public List<Producto> listar(){
        return productoService.listar();
    }

    @Operation(summary = "Crear un producto. Al ingresar los datos de la categoría del producto se debe ingresar solo el id de esta (el campo se escribe id, a secas).", description ="Crea un producto en base a los datos ingresados")
    @PostMapping
    public Producto guardar(@Valid @RequestBody Producto producto){
        return productoService.crearProducto(producto);
    }

    @Operation(summary = "Obtener un producto mediante su Id", description ="Retorna el producto cuya Id coincide con la ingresada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
        @ApiResponse(responseCode = "404", description = "No existe el paciente que busca", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity <Producto> buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));
    }

    @Operation(summary = "Actualizar un producto", description ="Actualiza un producto en base a los datos ingresados")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificar(@Valid @PathVariable Long id, @RequestBody Producto producto){
        try {
            ResponseEntity<Producto> prod = productoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

      
            prod.getBody().setPrecio(producto.getPrecio());
            

            productoService.crearProducto(prod.getBody());

            return ResponseEntity.ok(prod.getBody());
            

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Borrar una categoría", description ="Borra la categoría cuya Id coincida con la que fue ingresada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Filtrar por categoría", description ="Muestra los productos cuya categoría coincide con la ingresada")
    @GetMapping("/filtrarCategoria/{id}")
    public List<Producto> buscarPorCategoria(@PathVariable Long id) {
        return productoService.buscarPorCategoria(id);
    }
}
