package com.servicio.despachos.model;

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
@Table(name = "despacho")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un despacho en el sistema")
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha del despacho no puede estar vacía")
    @Schema(description = "Fecha del despacho" , requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fechaDesp;

    @NotBlank(message = "La calle no puede estar vacía")
    @Schema(description = "Calle de la dirección" , example = "Hugo Bravo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String calleDireccion;

    @NotBlank(message = "El número no puede estar vacío")
    @Schema(description = "Número de la dirección" , example = "7455", requiredMode = Schema.RequiredMode.REQUIRED)
    private String numDireccion;

    @Schema(description = "Id de la venta asociada al comprobante" , requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idVenta;

    @Schema(description = "Datos detallados del usuario. Se cargan en tiempo de ejecución via WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosVenta;
}
