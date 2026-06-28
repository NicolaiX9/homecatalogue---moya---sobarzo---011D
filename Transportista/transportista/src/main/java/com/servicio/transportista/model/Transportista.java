package com.servicio.transportista.model;

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
@Data
@Table(name = "transportista")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un transportista en el sistema")
public class Transportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre del transportista" , example = "Correos de Chile", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "El rut no puede estar vacío")
    @Schema(description = "Rut del transportista" , example = "66543765-2", requiredMode = Schema.RequiredMode.REQUIRED)
    private String rut;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Schema(description = "Correo electrónico del transportista", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

}
