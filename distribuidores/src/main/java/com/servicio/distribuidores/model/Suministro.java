package com.servicio.distribuidores.model;

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
@Table(name = "suministro")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un suministro en el sistema")
public class Suministro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Id del producto del microservicio de gestión de productos.")
    private Long idProducto;

    @NotNull(message = "El costo no puede estar vacío")
    @Schema(description = "Costo del suministro" , example = "50500", requiredMode = Schema.RequiredMode.REQUIRED)
    private int costo;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Schema(description = "Cantidad del producto del suministro" , example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    private int cantidad;

    @Schema(description = "Id del distribuidor del suministro.")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="distribuidor_id")
    private Distribuidor distribuidor;

    @Schema(description = "Datos detallados del producto. Se cargan en tiempo de ejecución via WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosProducto;


}
