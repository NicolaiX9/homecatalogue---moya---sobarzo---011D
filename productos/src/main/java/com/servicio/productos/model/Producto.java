package com.servicio.productos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un producto en el sistema")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Schema(description = "Nombre del producto" , requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "La descripción del producto no puede estar vacía")
    @Schema(description = "Descripción del producto" , requiredMode = Schema.RequiredMode.REQUIRED)
    private String descripcion;

    @NotNull(message = "El precio del producto no puede estar vacío")
    @Schema(description = "Precio del producto" , requiredMode = Schema.RequiredMode.REQUIRED)
    private int precio;

    @Schema(description = "Id de la categoría del producto", accessMode = Schema.AccessMode.READ_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="producto_categoria_id")
    private ProductoCategoria productoCategoria;
   
    
    

}
