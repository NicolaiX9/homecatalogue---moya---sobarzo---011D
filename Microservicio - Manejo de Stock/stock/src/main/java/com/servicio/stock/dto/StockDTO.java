package com.servicio.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa el stock unificado con los datos externos de otros microservicios")
public class StockDTO {

    private Long id;
    private int cantidad;
    private Long idProducto;
    private Long idAlmacen;

    @Schema(description = "Datos detallados del producto obtenidos en tiempo de ejecución", accessMode = Schema.AccessMode.READ_ONLY)
    private ProductoDTO datosProducto;

    @Schema(description = "Datos detallados del alamcen obtenidos en tiempode ejecución", accessMode = Schema.AccessMode.READ_ONLY)

    private AlmacenDTO datosAlmacen;
}
