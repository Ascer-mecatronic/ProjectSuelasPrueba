package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tipo;


public interface TipoRepository extends CrudRepository<Tipo, Long>{

    Optional<Tipo> findByName(String name);

    @Query("select t from Tipo t left join fetch t.products where t.name=?1")
    Optional<Tipo> findOneWithProducts(String name);

}
