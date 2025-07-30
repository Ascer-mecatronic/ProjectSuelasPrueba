package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Color;


public interface ColorRepository extends CrudRepository<Color, Long>{

    Optional<Color> findByName(String name);

    @Query("select c from Color c left join fetch c.products where c.name=?1")
    Optional<Color> findOneWithProducts(String name);

}
