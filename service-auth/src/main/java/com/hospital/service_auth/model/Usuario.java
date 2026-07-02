package com.hospital.service_auth.model;

import java.util.HashSet;
import java.util.Set;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id 
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre del usuario" , example = "Pedro", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombreUsuario;
    
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Schema(description = "Contraseña del usuario" , example = "ABC1234", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contrasena;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Schema(description = "Correo electrónico del usuario" , example = "Pedro@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String correo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles =new HashSet<>();
}
