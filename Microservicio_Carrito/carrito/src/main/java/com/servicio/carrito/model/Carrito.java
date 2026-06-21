package com.servicio.carrito.model;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

    private int total;

    @Schema(description = "Id del usuario del microservicio de gestión de usuarios.")
    private Long idUsuario;

    @Schema(description = "Datos detallados del usuario. Se cargan en tiempo de ejecución via WebClient", accessMode = Schema.AccessMode.READ_ONLY)
    @Transient
    private Object datosUsuario;

}
