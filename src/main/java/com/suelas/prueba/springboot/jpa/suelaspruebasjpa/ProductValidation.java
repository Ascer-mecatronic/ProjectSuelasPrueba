package com.suelas.prueba.springboot.jpa.suelaspruebasjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Product;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.ProductRequest;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.ProductService;
import java.util.Optional;

@Component
public class ProductValidation implements Validator {

    @Autowired
    private ProductService service;

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductRequest.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        ProductRequest product = (ProductRequest) target;
        // ValidationUtils.rejectIfEmpty(errors, "tipo", null, "No puede ser nulo!");
        // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tamanio", null, "No puede
        // ser nulo!");

        if (product.getId() == null) {
            errors.rejectValue("id", "No puede ser nulo");
        }else if (!product.getId().equals(0L)) {
            Optional<Product> op = service.findById(product.getId());
            if (!op.isPresent()) {
                errors.rejectValue("id",  "Producto inexistente");
            }
        }

        if (product.getName().isBlank() || product.getName() == null) {
            errors.rejectValue("name",  "No puede ser nulo");
        }

        if (product.getPrecio() == null || product.getPrecio().equals(0L)) {
            errors.rejectValue("precio", "No puede ser nulo, ni cero");
        }
            
        if (product.getTamanio() == null || product.getTipo().isBlank()) {
            errors.rejectValue("tamanio",  "No pude ser nulo");
        }

        if (product.getTipo() == null || product.getTipo().isBlank()) {
            errors.rejectValue("tipo",  "No pude ser nulo");
        }

        if (product.getColor() == null || product.getColor().isBlank()) {
            errors.rejectValue("color",  "No pude ser nulo");
        }

        if (product.getTallas() == null) {
            errors.rejectValue("tallas",  "No puede ser nulo!");
        } else {
            for (String tallas : product.getTallas()) {
                if (tallas.isBlank()) {
                    errors.rejectValue("tallas",  "No puede ser nulo ni vacio");
                }
            }
        }

        long maxFileSize = 5 * 1024 * 1024;
        if (product.getFiles().isEmpty() || product.getFiles() == null) {
            errors.rejectValue("files",  "El campo no puede quedar vacio, requeridas 3 imagenes");
        } else if (product.getFiles().size() < 3 || product.getFiles().size() > 3) {
            errors.rejectValue("files",  "Requeridas tres imagenes para el articulo");
        } else {
            for (MultipartFile file : product.getFiles()) {
                if (file.getSize() > maxFileSize) {
                    errors.rejectValue("files",  "El tamaño del archivo no pude ser mayor a 5Mb o esta vacio");
                }
                if (!file.getOriginalFilename().endsWith(".jpg") &&
                        !file.getOriginalFilename().endsWith(".jpeg") &&
                        !file.getOriginalFilename().endsWith(".png")) {
                    errors.rejectValue("files", "Solo permitidos archivos JPG, JPEG, PNG ");
                }

            }

        }

    }

}
