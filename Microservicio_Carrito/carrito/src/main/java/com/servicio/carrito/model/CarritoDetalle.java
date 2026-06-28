package com.servicio.carrito.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito_detalle")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa el detalle de un carrito en el sistema")
public class CarritoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Schema(description = "Id del producto del microservicio de gestión de productos.")
    private Long idProducto;

    @NotNull(message = "El total no puede estar vacío")
    @Schema(description = "Cantidad de unidades del producto en el carrito" , example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    private int cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="carrito_id")
    private Carrito carrito;

    @Schema(description = "Datos detallados del producto. Se cargan en tiempo de ejecución via WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosProducto;

}
