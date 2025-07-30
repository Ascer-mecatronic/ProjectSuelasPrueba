package com.suelas.prueba.springboot.jpa.suelaspruebasjpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Talla;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.TallaService;

@Component
public class TallaValidation  implements Validator{

    @Autowired
    private TallaService service;

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Talla.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@SuppressWarnings("null") Object target, @SuppressWarnings("null") Errors errors) {
        Talla talla = (Talla) target;

        if (talla.getId() == null) {
            errors.rejectValue("id",  "No puede ser nulo");
        }else if (!talla.getId().equals(0L)) {
            Optional<Talla> op = service.findById(talla.getId());
            if (!op.isPresent()) {
                errors.rejectValue("id",  "Producto inexistente");
            }
        }

        if (talla.getName().isBlank() || talla.getName() == null) {
            errors.rejectValue("name",  "No puede ser nulo");
        }else if (service.existByName(talla.getName())) {
            errors.rejectValue("name",  "Ya existe en la base de datos");
        }
    }

}
