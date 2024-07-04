package com.example.ecf4gestionemployes.exception;


import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
