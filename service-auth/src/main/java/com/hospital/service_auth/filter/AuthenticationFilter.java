package com.hospital.service_auth.filter;

import org.springframework.stereotype.Component;

import lombok.Value;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

    @Value("${jwt.secret}")
    private String secreto;
    public AuthenticationFilter(){
        super(Config.class);
    }
    public static class Config {

    }
}
