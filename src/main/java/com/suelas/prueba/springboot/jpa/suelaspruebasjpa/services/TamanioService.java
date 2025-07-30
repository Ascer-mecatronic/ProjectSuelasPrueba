package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tamanio;

public interface TamanioService {

    List<Tamanio> findAll();

    Optional<Tamanio> findByname(String name);

    Optional<Tamanio> findOneWhitProds(String name);

}
