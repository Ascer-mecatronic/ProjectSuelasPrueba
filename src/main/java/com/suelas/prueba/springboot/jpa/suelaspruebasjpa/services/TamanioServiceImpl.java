package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tamanio;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.TamanioRepository;

@Service
public class TamanioServiceImpl implements TamanioService{

    @Autowired
    private TamanioRepository tamanioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Tamanio> findByname(String name) {
        return tamanioRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tamanio> findOneWhitProds(String name) {
        return tamanioRepository.findOneWithProducts(name);
    }

    @Override
    public List<Tamanio> findAll() {
        return (List<Tamanio>) tamanioRepository.findAll();
    }

}
