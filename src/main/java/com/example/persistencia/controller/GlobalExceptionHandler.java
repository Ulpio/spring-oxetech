package com.example.persistencia.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> tratamento404(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado");
    }

    @ExceptionHandler
    public ResponseEntity<String> tratamentoGenerico(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("erro interno:" + ex.getMessage());
    }
}
