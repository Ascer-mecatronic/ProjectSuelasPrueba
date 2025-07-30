package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Cantidad;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.CantidadRepository;

@Service
public class CantidadServiceImpl implements CantidadService{

    @Autowired
    private CantidadRepository cantidadRepository;

    @Override
    @Transactional
    public Optional<Cantidad> findById(Long id) {
       return cantidadRepository.findById(id);
    }

}
