package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Color;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.ColorService;

@RestController
@RequestMapping("/colores")
@CrossOrigin(originPatterns = "*")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("/{name}")
    public ResponseEntity<?> show(@PathVariable String name){
        Optional<Color> opColor = colorService.findOneWhitProds(name);
        if (opColor.isPresent()) {
            return ResponseEntity.ok(opColor.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
