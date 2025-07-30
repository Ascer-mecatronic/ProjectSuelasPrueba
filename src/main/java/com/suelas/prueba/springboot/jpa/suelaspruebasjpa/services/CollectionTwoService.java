package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.CollectionTwo;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.PagesRequest;

public interface CollectionTwoService {

    List<CollectionTwo> findAll();

     int garbageFileCollectionTwo();

     List<CollectionTwo> saveCollectionTwo(PagesRequest pages);
}
