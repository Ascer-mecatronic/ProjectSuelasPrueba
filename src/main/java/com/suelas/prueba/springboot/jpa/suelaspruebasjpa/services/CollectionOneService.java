package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.CollectionOne;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.PagesRequest;

public interface CollectionOneService {

    List<CollectionOne> saveCollectionOne(PagesRequest pages);

    int garbageFileCollectionOne();

    List<CollectionOne> findAll();

}
