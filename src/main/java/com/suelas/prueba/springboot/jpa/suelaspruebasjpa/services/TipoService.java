package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tipo;

public interface TipoService {

    List<Tipo> findAll();

    Optional<Tipo> findOneByName(String name);

    Optional<Tipo> findOneWhitProds(String name);

}
