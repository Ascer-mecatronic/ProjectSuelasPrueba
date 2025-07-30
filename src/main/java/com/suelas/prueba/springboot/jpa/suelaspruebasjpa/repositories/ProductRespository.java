package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Product;

public interface ProductRespository extends CrudRepository<Product, Long>{

    @Query("select p from Product p left join fetch p.tallas where p.name=?1")
    Optional<Product> findOneWithTallas(String name);

    boolean existsByName(String name);

    @Query("select p from Product p where p.id in ?1")
    public List<Product> findProductsByIds(List<Long> list);

}
