package com.hospital.service_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import com.hospital.service_auth.dto.AuthRequest;
import com.hospital.service_auth.model.Usuario;
import com.hospital.service_auth.service.AuthService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/auth")
@SecurityScheme(
		  name = "Bearer Authentication",
		  type = SecuritySchemeType.HTTP,
		  bearerFormat = "JWT",
		  scheme = "bearer"
		)
@Tag(name = "Autenticación", description = "Endpoints para registro y login de usuario")

public class AutenticacionController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Registrar un nuevo usuario. Al ingresar los datos del rol se debe ingresar solo el id de este (el campo se escribe id, a secas) Los id disponibles son 1 (Cliente) y 2 (Administrador).", description= "Guarda el nuevo usuario con la contraseña encriptada")
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar (@RequestBody Usuario usuario){
        return ResponseEntity.ok(authService.registrar(usuario));
    }

    @Operation(summary = "Iniciar sesión", description= "Retorna un Token JWT si las credenciales son válidas")
    @PostMapping("/login")
    public ResponseEntity<String> registrar (@RequestBody AuthRequest request){

        try{
            String token = authService.login(request.getNombreUsuario(), request.getPassword());
            return ResponseEntity.ok(token);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

}
