package com.servicio.stock.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un stock en el sistema")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;


    @NotNull(message = "La cantidad no puede estar vacía")
    @Schema(description = "Cantidad del producto en el stock" , example = "40", requiredMode = Schema.RequiredMode.REQUIRED)
    private int cantidad;


    @Schema(description = "Id del producto del microservicio de gestión de productos.")
    private Long idProducto;

    @Schema(description = "Id del almacén del microservicio de gestión de almacenes.")
    private Long idAlmacen;

    @Schema(description = "Datos detallados del producto. Se cargan en tiempo de ejecución via WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosProducto;

    @Schema(description = "Datos detallados del almacén. Se cargan en tiempo de ejecución via WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosAlmacen;
    

}
