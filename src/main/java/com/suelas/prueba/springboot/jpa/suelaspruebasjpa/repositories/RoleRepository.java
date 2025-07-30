package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

    Optional<Role> findByName(String name);

}
