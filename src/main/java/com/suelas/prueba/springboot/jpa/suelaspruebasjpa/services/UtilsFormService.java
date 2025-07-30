package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.UtilsForm;

@Service
public class UtilsFormService {

     @Autowired
    private TallaService tallaService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private TipoService tipoService;

    @Autowired
    private TamanioService tamanioService;

    @Transactional(readOnly = true)
    public UtilsForm listsForm() {

        UtilsForm utilsForm = new UtilsForm();

        utilsForm.setTallas(tallaService.findAll());
        utilsForm.setColores(colorService.findAll());
        utilsForm.setTamanios(tamanioService.findAll());
        utilsForm.setTipos(tipoService.findAll());

        return utilsForm;

    }

}
