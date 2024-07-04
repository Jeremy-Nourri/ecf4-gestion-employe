package com.example.ecf4gestionemployes.utils;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;

import java.io.IOException;

public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin","*");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods","GET,POST,DELETE,PUT");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Header","Content-Type,Authorization");

    }

}
