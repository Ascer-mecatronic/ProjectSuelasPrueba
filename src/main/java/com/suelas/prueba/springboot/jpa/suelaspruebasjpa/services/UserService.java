package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.List;
import java.util.Optional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.UserDto;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.User;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.UserRequest;

public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(User user);

    Optional<UserDto> update(UserRequest user, Long id);

    boolean existByUsername(String username);

    void remove(Long id);

}
