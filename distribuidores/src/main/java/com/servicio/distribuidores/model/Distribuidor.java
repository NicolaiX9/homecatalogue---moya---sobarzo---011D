package com.servicio.distribuidores.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "distribuidor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un distribuidor en el sistema")
public class Distribuidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El rut del distribuidor no puede estar vacío")
    @Schema(description = "Rut de la empresa" , example = "23435345-4", requiredMode = Schema.RequiredMode.REQUIRED)
    private String rutEmpresa;
    
    @NotBlank(message = "La razón social de la empresa no puede estar vacía")
    @Schema(description = "Razón social del distribuidor" , requiredMode = Schema.RequiredMode.REQUIRED)
    private String razonSocial;

    @NotNull(message = "El número de la empresa no puede estar vacío")
    @Schema(description = "Número telefónico del distribuidor" , requiredMode = Schema.RequiredMode.REQUIRED)
    private int telefono;

    @NotBlank(message = "El email de la empresa no puede estar vacío")
    @Schema(description = "El correo electrónico del distribuidor" , requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "La calle no puede estar vacía")
    @Schema(description = "Calle de la dirección" , example = "Hugo Bravo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String calleDireccion;

    @NotBlank(message = "El número no puede estar vacío")
    @Schema(description = "Número de la dirección" , example = "7455", requiredMode = Schema.RequiredMode.REQUIRED)
    private String numeroDireccion;
}
