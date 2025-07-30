package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages.Pages;

public interface PagesRepository extends CrudRepository <Pages, Long> {

}
