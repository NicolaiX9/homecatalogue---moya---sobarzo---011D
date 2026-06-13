package com.api.gateway.filter;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;



@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>
{

    @Value("${jwt.secret}")
    private String secreto;
    public AuthenticationFilter(){
        super(Config.class);
    }
    public static class Config {

    }
    @Override
    public GatewayFilter apply(Config config) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }
}
