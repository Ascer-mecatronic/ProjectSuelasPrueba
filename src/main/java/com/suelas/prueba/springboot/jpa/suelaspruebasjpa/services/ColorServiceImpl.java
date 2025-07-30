package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Color;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.ColorRepository;

@Service
public class ColorServiceImpl implements ColorService{

    @Autowired
    private ColorRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Color> findOneWhitProds(String name) {
        return repository.findOneWithProducts(name);
    }

    @Override
    public List<Color> findAll() {
        return (List<Color>) repository.findAll();
    }

}
