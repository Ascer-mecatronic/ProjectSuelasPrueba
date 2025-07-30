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

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.ProductValidation;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Product;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.ProductRequest;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@CrossOrigin(originPatterns = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidation validation;

    @GetMapping
    public List<Product> list() {
        productService.findAll().forEach(p -> {
            System.out.println( "3------------->"+ p);
        });
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Product> optionalProd = productService.findById(id);
        if (optionalProd.isPresent()) {
            return ResponseEntity.ok(optionalProd.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/optimize")
    public ResponseEntity<?> optimize(){
        int filesDeleted = productService.garbageImages();
        return ResponseEntity.ok(filesDeleted);
    }

    @PostMapping(path = "/picture", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> create(@Valid @ModelAttribute ProductRequest product, BindingResult result) {
        validation.validate(product, result);
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));

    }

    @PutMapping(path = "/update", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> update(@Valid @ModelAttribute ProductRequest product, BindingResult result) {
        validation.validate(product, result);
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Product> optionalProd;

        optionalProd = productService.update(product);
        if (optionalProd.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.onlySave(optionalProd.orElseThrow()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Product> op = productService.findById(id);
        if (op.isPresent()) {
            productService.delete(id);
            ResponseEntity.noContent().build();
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
