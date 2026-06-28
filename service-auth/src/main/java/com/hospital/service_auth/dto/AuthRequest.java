package com.hospital.service_auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Aquí se guardan temporalmente los datos del Json que envía el usuario

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre del usuario" , example = "Pedro", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreUsuario;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Schema(description = "Contraseña del usuario" , example = "ABC1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;


}
