package com.servicio.almacenes.model;

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
@Table(name = "almacen")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un almacén en el sistema")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "La calle no puede estar vacía")
    @Schema(description = "Calle de la dirección" , example = "Hugo Bravo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String calleDireccion;
    @NotBlank(message = "El número no puede estar vacío")
    @Schema(description = "Número de la dirección" , example = "7455", requiredMode = Schema.RequiredMode.REQUIRED)
    private String numeroDireccion;
}
