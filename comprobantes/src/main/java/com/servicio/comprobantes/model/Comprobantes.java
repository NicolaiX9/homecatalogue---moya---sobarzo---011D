package com.servicio.comprobantes.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comprobantes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un comprobante en el sistema")
public class Comprobantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número del comprobante no puede estar vacío")
    @Schema(description = "Número del comprobante", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nmroComprobante;

    @NotBlank(message = "La url no puede estar vacía")

    @Schema(description = "Url del pdf del comprobante", requiredMode = Schema.RequiredMode.REQUIRED)
    private String pdfUrl;

    @NotNull(message = "El total no puede estar vacío")
    @Schema(description = "Total de la venta" , example = "90500", requiredMode = Schema.RequiredMode.REQUIRED)
    private int total;

    @Schema(description = "Id de la venta asociada al comprobante" , requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idVenta;

    @Schema(description = "Datos detallados del usuario. Se cargan en tiempo de ejecución via WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosVenta;


    @NotNull(message = "La fecha de emisión no puede estar vacía")
    @Schema(description = "Fecha de emisión del comprobante", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaEmision;
}
