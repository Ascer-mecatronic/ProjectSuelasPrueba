package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tamanio;

public interface TamanioRepository extends CrudRepository<Tamanio, Long>{

    Optional<Tamanio> findByName(String name);

    @Query("select t from Tamanio t left join fetch t.products where t.name=?1")
    Optional<Tamanio> findOneWithProducts(String name);

}
