package com.hospital.service_auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Aquí se guardan temporalmente los datos del Json que envía el usuario

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String nombreUsuario;
    private String password;


}
