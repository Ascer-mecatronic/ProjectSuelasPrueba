package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.exceptions.HandleFileException;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Product;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.ProductRequest;

public interface ProductService {

    List<Product> findAll();

    boolean existsByName(String name);

    Optional<Product> findById(Long id);

    Product save(ProductRequest product) throws HandleFileException;

    Product onlySave(Product product);

    Optional<Product> update(ProductRequest product) throws HandleFileException;

    Optional<Product> delete(Long id);

    int garbageImages();

}
