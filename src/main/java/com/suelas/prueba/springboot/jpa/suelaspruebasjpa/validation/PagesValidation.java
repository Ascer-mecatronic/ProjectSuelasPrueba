package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.Pages;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.PagesRequest;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services.PagesService;


@Component
public class PagesValidation implements Validator{

    @Autowired 
    private PagesService pagesService;

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return PagesRequest.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        PagesRequest pagesRequest = (PagesRequest) target;
        long maxFileSize = 5 * 1024 * 1024;

        if (pagesRequest.getId() == null) {
            errors.rejectValue("id", null, "No puede ser nulo");
        }else if (!pagesRequest.getId().equals(0L)) {
            Optional<Pages> op = pagesService.findById(pagesRequest.getId());
            if (!op.isPresent()) {
                errors.rejectValue("id",null,  "Pagina inexistente");
            }
        }

        if (pagesRequest.getName().isBlank() || pagesRequest.getName() == null) {
            errors.rejectValue("name",null,  "No puede ser nulo");
        }

        if (pagesRequest.getTextOne().isBlank() || pagesRequest.getTextOne() == null) {
            errors.rejectValue("textOne",null, "No puede ser nulo");
        }

        if (pagesRequest.getTextTwo().isBlank() || pagesRequest.getTextTwo() == null) {
            errors.rejectValue("textTwo",null, "No puede ser nulo");
        }

        if (pagesRequest.getImageOne().isEmpty() || pagesRequest.getImageOne() == null) {
            errors.rejectValue("imageOne",null,  "no puede quedar vacio");
        }else {
            if (pagesRequest.getImageOne().getSize() > maxFileSize) {
                errors.rejectValue("imageOne",null,  "El tamaño del archivo no pude ser mayor a 5Mb");
            }
            if (!pagesRequest.getImageOne().getOriginalFilename().endsWith(".jpg") &&
                        !pagesRequest.getImageOne().getOriginalFilename().endsWith(".jpeg") &&
                        !pagesRequest.getImageOne().getOriginalFilename().endsWith(".png")) {
                    errors.rejectValue("imageOne",null, "Solo permitidos archivos JPG, JPEG, PNG ");
                }
        }

        if (pagesRequest.getImageTwo().isEmpty() || pagesRequest.getImageTwo() == null) {
            errors.rejectValue("imageTwo",null,  "no puede quedar vacio");
        }else {
            if (pagesRequest.getImageTwo().getSize() > maxFileSize) {
                errors.rejectValue("imageTwo",null,  "El tamaño del archivo no pude ser mayor a 5Mb");
            }
            if (!pagesRequest.getImageTwo().getOriginalFilename().endsWith(".jpg") &&
                        !pagesRequest.getImageTwo().getOriginalFilename().endsWith(".jpeg") &&
                        !pagesRequest.getImageTwo().getOriginalFilename().endsWith(".png")) {
                    errors.rejectValue("imageTwo",null, "Solo permitidos archivos JPG, JPEG, PNG ");
                }
        }

        if (pagesRequest.getImageThree().isEmpty() || pagesRequest.getImageThree() == null) {
            errors.rejectValue("imageThree",null,  " no puede quedar vacio");
        }else {
            if (pagesRequest.getImageThree().getSize() > maxFileSize) {
                errors.rejectValue("imageThree",null,  "El tamaño del archivo no pude ser mayor a 5Mb");
            }
            if (!pagesRequest.getImageThree().getOriginalFilename().endsWith(".jpg") &&
                        !pagesRequest.getImageThree().getOriginalFilename().endsWith(".jpeg") &&
                        !pagesRequest.getImageThree().getOriginalFilename().endsWith(".png")) {
                    errors.rejectValue("imageThree",null, "Solo permitidos archivos JPG, JPEG, PNG ");
                }
        }


         if (pagesRequest.getFilesOne().isEmpty() || pagesRequest.getFilesOne() == null) {
            errors.rejectValue("filesOne",  null," no puede quedar vacio, requeridas 3 imagenes");
        } else if (pagesRequest.getFilesOne().size() < 3 || pagesRequest.getFilesOne().size() > 3) {
            errors.rejectValue("filesOne", null,  "Requeridas tres imagenes para el articulo");
        } else {
            for (MultipartFile file : pagesRequest.getFilesOne()) {
                if (file.getSize() > maxFileSize) {
                    errors.rejectValue("filesOne", null,  "El tamaño del archivo no pude ser mayor a 5Mb o esta vacio");
                }
                if (!file.getOriginalFilename().endsWith(".jpg") &&
                        !file.getOriginalFilename().endsWith(".jpeg") &&
                        !file.getOriginalFilename().endsWith(".png")) {
                    errors.rejectValue("filesOne", null, "Solo permitidos archivos JPG, JPEG, PNG ");
                }

            }

        }

         if (pagesRequest.getFilesTwo().isEmpty() || pagesRequest.getFilesTwo() == null) {
            errors.rejectValue("filesTwo",null,  "no puede quedar vacio, requeridas 3 imagenes");
        } else if (pagesRequest.getFilesTwo().size() < 3 || pagesRequest.getFilesTwo().size() > 3) {
            errors.rejectValue("filesTwo", null, "Requeridas tres imagenes para el articulo");
        } else {
            for (MultipartFile file : pagesRequest.getFilesTwo()) {
                if (file.getSize() > maxFileSize) {
                    errors.rejectValue("filesTwo", null, "El tamaño del archivo no pude ser mayor a 5Mb o esta vacio");
                }
                if (!file.getOriginalFilename().endsWith(".jpg") &&
                        !file.getOriginalFilename().endsWith(".jpeg") &&
                        !file.getOriginalFilename().endsWith(".png")) {
                    errors.rejectValue("filesTwo",null, "Solo permitidos archivos JPG, JPEG, PNG ");
                }

            }

        }

        
    }

}
