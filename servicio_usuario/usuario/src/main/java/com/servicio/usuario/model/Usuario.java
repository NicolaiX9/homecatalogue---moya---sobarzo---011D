package com.servicio.usuario.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un usuario en el sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    
    @NotBlank(message = "El run no puede estar vacío")
    @Schema(description = "Run del usuario" , example = "28675876-2", requiredMode = Schema.RequiredMode.REQUIRED)
    private String run;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre del usuario" , example = "Javier Barrera", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Schema(description = "Correo electrónico del usuario" , example = "javierbarrer@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacío")
    @Schema(description = "Contraseña del usuario" , requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tipo_usuario_id")
    private TipoUsuario tipoUsuario;


    


}
