package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String name);

}
