package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Talla;

public interface TallaRepository extends CrudRepository<Talla, Long>{

    Optional<Talla> findByName(String name);

    boolean existsByName(String name);
    
    @Query("select t from Talla t left join fetch t.products where t.name=?1")
    Optional<Talla> findOneWithProducts(String name);

    @Query("select t from Talla t left join fetch t.products where t.id=?1")
    Optional<Talla> findOneWithProductsById(Long id);

}
