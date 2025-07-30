package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.Optional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Cantidad;

public interface CantidadService {

    Optional<Cantidad> findById(Long id);

}
