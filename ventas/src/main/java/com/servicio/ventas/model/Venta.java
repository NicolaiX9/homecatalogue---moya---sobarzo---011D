package com.servicio.ventas.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
    private int total;
    private LocalDate fecha;
    private Long idCarrito;

    @Transient
    private Object datosCarrito;
    

}
