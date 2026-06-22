package com.servicio.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlmacenDTO {

    private Long id;
    private String calleDireccion;
    private String numeroDireccion;
}
