package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.Pages;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.PagesRequest;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.PagesService;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.validation.PagesValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pages")
@CrossOrigin(originPatterns = "*")
public class PagesController {

    @Autowired
    PagesService pagesService;

    @Autowired
    private PagesValidation validation;

    @GetMapping
    public List<Pages> list(){
        return pagesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Optional<Pages> opp = pagesService.findById(id);
        if (opp.isPresent()) {
            return ResponseEntity.ok(opp.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/garbageOne")
    public ResponseEntity<?> garbageOne(){
        int filesDelete = pagesService.garbageCollectOne();
        return ResponseEntity.ok(filesDelete);
    }

    @GetMapping("/garbageTwo")    
    public ResponseEntity<?> garbageTwo(){
        int filesDelete = pagesService.garbageCollectTwo();
        return ResponseEntity.ok(filesDelete);
    }

    @PostMapping(path = "/files", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> create(@Valid @ModelAttribute PagesRequest pagesRequest, BindingResult result){

        validation.validate(pagesRequest, result);
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pagesService.save(pagesRequest));
    }

    @PutMapping(path = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?>update(@Valid @ModelAttribute PagesRequest pagesRequest, BindingResult result){
        validation.validate(pagesRequest, result);
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Pages> op = pagesService.update(pagesRequest);
        if (op.isPresent()) {
            return ResponseEntity.status(201).body(op.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>remove(@PathVariable Long id){
        Optional<Pages> op = pagesService.remove(id);
        if (op.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
