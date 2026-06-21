package com.servicio.distribuidores.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;
    private String rutEmpresa;
    private String razonSocial;
    private int telefono;
    private String email;
    private String calleDireccion;
    private String numeroDireccion;
}
