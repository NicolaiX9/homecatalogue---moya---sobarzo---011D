package com.servicio.productos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto_categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa una categoría de producto en el sistema")
public class ProductoCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La categoría de producto no puede estar vacía")
    @Schema(description = "Categoría de producto" , requiredMode = Schema.RequiredMode.REQUIRED)
    private String categoria;
}
