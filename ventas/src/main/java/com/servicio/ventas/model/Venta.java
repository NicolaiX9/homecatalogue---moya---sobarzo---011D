package com.servicio.ventas.model;

import java.time.LocalDate;

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
@Data
@Table(name = "venta")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa una venta en el sistema")
public class Venta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    
    @NotNull(message = "El total de la venta no puede estar vacío")
    @Schema(description = "Total de la venta" , example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    private int total;

    @NotNull(message = "La fecha de la venta no puede estar vacía")
    @Schema(description = "La fecha de la venta" , example = "20-05-2026", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fecha;

    @Schema(description = "Id del carrito del microservicio de gestión de carritos.")
    private Long idCarrito;

    @Schema(description = "Datos detallados del carrito. Se cargan en tiempo de ejecución via WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosCarrito;
    

}
