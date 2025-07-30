package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.Pages;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.PagesRequest;

public interface PagesService {

    List<Pages> findAll();

    Optional<Pages> findById(Long id);

    Pages onlySave(Pages pages);

    Pages save(PagesRequest pages);

    Optional<Pages> update(PagesRequest pages);

    Optional<Pages>remove(Long id);

    int garbageCollectOne();

    int garbageCollectTwo();

}
