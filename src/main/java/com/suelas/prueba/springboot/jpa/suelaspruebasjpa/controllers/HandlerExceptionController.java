package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.AttributeTallaException;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.ErrorDto;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.FileReadException;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.FileWriteException;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({FileReadException.class, FileWriteException.class})
    public ResponseEntity<?> errorMultipart(Exception ex){
        ErrorDto error = new ErrorDto();
        error.setDate(new Date());
        error.setError("Error de procesamiento de archivo multimedia");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(AttributeTallaException.class)
    public ResponseEntity<?>attributeError(Exception ex){
        ErrorDto error = new ErrorDto();
        error.setDate(new Date());
        error.setError("Accion no completada!");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?>Error(Exception ex){
        HashMap<String, String> error =  new HashMap<>();
        error.put("name", "Este nombre ya existe en el registro");
        return ResponseEntity.internalServerError().body(error);
    }


}
