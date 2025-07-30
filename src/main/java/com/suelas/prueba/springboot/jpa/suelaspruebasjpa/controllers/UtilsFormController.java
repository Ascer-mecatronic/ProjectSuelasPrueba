package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.UtilsForm;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.UtilsFormService;

@RestController
@RequestMapping("/listado")
@CrossOrigin(originPatterns = "*")
public class UtilsFormController {

    @Autowired
    private UtilsFormService utilsFormService;

    @GetMapping
    public ResponseEntity<UtilsForm> listsForm(){
        return ResponseEntity.ok(utilsFormService.listsForm());
    }

}
