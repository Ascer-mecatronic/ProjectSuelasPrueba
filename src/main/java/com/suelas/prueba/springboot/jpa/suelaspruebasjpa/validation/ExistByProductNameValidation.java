package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByProductNameValidation implements ConstraintValidator<ExistByProductName, String>{

    @Autowired
    private ProductService productService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (productService == null) {
            return true;
        }
        return !productService.existsByName(value);
    }

}
