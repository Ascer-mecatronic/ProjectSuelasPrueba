package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tipo;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.TipoRepository;

@Service
public class TipoServiceImpl implements TipoService{

    @Autowired
    private TipoRepository tipoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Tipo> findOneByName(String name) {
       return tipoRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tipo> findOneWhitProds(String name) {
        return tipoRepository.findOneWithProducts(name);
    }

    @Override
    public List<Tipo> findAll() {
        return (List<Tipo>) tipoRepository.findAll();
    }

}
