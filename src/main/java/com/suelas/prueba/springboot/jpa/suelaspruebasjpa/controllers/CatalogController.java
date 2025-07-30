package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.DetailsDto;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Product;
//import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.ProductService;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.CatalogService;

//import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.ProductService;


@RestController
@RequestMapping("/catalog")
@CrossOrigin(originPatterns = "*")
public class CatalogController {

   @Autowired
   private CatalogService catalogService;

    @PutMapping()
    public ResponseEntity<?> show(@RequestBody DetailsDto details){

       List<Product> outputList = catalogService.listTallaDetail(details);
       if (!outputList.isEmpty()) {
        return ResponseEntity.ok(outputList);
       }
       return ResponseEntity.notFound().build();
    }

}
