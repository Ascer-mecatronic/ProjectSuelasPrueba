package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Color;

public interface ColorService {

    List<Color> findAll();

    Optional<Color> findOneWhitProds(String name);

}
