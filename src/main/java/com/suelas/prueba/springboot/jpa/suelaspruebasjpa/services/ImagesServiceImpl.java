package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Images;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.ImagesRepository;

@Service
public class ImagesServiceImpl implements ImagesService{

    @Autowired
    private ImagesRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Images> findAll() {
        return (List<Images>) repository.findAll();
    }

}
