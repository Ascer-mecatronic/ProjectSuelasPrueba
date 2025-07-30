package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tamanio;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.TamanioService;

@RestController
@RequestMapping("/tamanios")
@CrossOrigin(originPatterns = "*")
public class TamanioController {

    @Autowired
    private TamanioService tamanioService;

    @GetMapping("/{name}")
    public ResponseEntity<?> show(@PathVariable String name){
        Optional<Tamanio> opTamanio = tamanioService.findOneWhitProds(name);
        if (opTamanio.isPresent()) {
            return ResponseEntity.ok().body(opTamanio.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
