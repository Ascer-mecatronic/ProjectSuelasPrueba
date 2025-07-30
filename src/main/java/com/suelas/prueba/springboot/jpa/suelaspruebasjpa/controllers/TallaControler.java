package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.TallaValidation;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Talla;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.TallaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tallas")
@CrossOrigin(originPatterns = "*")
public class TallaControler {

    @Autowired
    private TallaService tallaService;

    @Autowired
    private TallaValidation validation;

    @GetMapping
    public List<Talla> list(){
        return tallaService.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> show(@PathVariable String name){
        Optional<Talla> opTalla = tallaService.findOneWhitProds(name);

        if (opTalla.isPresent()) {
            return ResponseEntity.ok(opTalla.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Talla talla, BindingResult result){
        validation.validate(talla, result);
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tallaService.save(talla));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Talla talla, BindingResult result, @PathVariable Long id){
        validation.validate(talla, result);
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Talla> opTalla = tallaService.update(talla, id);
        if (opTalla.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(tallaService.save(opTalla.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>remove(@PathVariable Long id){
        Optional<Talla>opTalla = tallaService.findById(id);
        if (opTalla.isPresent()) {
            
                Optional<Talla>opTallaDelete = tallaService.deleteAll(id);
                if (opTallaDelete.isPresent()) {   
                    return ResponseEntity.noContent().build();
                }
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
