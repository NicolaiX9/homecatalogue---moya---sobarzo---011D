package com.servicio.carrito.model;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un carrito en el sistema")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El total no puede estar vacío")
    @Schema(description = "Calle de la dirección" , example = "Hugo Bravo", requiredMode = Schema.RequiredMode.REQUIRED)
    private int total;

    @Schema(description = "Id del usuario del microservicio de gestión de usuarios.")
    private Long idUsuario;

    @Schema(description = "Datos detallados del usuario. Se cargan en tiempo de ejecución via WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosUsuario;

}
