package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tipo;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.TipoService;

@RestController
@RequestMapping("/tipos")
@CrossOrigin(originPatterns = "*")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @GetMapping("/{name}")
    public ResponseEntity<?> show(@PathVariable String name){
        Optional<Tipo> tipoOptional = tipoService.findOneWhitProds(name);
        if (tipoOptional.isPresent()) {
            return ResponseEntity.ok().body(tipoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
