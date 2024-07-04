package com.example.ecf4gestionemployes.exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
