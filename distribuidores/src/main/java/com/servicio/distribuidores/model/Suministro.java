package com.servicio.distribuidores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suministro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suministro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProducto;
    private Long idDistribuidor;
    private int costo;
    private int cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="distribuidor_id")
    private Distribuidor distribuidor;

    @Transient
    private Object datosProducto;


}
