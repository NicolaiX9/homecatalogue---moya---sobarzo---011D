package com.servicio.comprobantes.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comprobantes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comprobantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nmroComprobante;
    private String pdfUrl;
    private int total;
    private Long idVenta;
    private LocalDate fechaEmision;
}
