package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.AttributeTallaException;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Talla;

public interface TallaService {

    List<Talla> findAll();

    Optional<Talla> findOneWhitProds(String name);

    Optional<Talla> findById(Long id);

    Talla save(Talla talla);

    Optional<Talla> update(Talla talla, Long id);

    Optional<Talla> delete(Long id);

    Optional<Talla> deleteAll(Long id) throws AttributeTallaException;

    boolean existByName(String name);

}
