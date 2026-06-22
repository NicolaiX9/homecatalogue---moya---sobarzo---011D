package com.servicio.usuario.model;

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
@Table(name="tipo_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un tipo de usuario en el sistema")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;


    @NotBlank(message = "El rol no puede estar vacío")
    @Schema(description = "El tipo de rol que pueden tener los usuarios" , example = "Administrador", requiredMode = Schema.RequiredMode.REQUIRED)
    private String rol;

}
