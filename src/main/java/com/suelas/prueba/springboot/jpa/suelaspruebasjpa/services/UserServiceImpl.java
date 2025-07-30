package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.IUser;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.UserDto;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.mapper.DtoMapperUser;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Role;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.User;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request.UserRequest;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.RoleRepository;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = (List<User>) userRepository.findAll();

        return users.stream()
        .map(u -> DtoMapperUser.builder().setUser(u).build())
        .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id){
        return userRepository.findById(id).map(u -> DtoMapperUser.builder().setUser(u).build());
    }


    @Override
    @Transactional
    public UserDto save(User user) {

        user.setRoles(getRoles(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return DtoMapperUser.builder().setUser(userRepository.save(user)).build();
    }

    @Override
    @Transactional
    public Optional<UserDto> update(UserRequest user, Long id){
        Optional<User> userById = userRepository.findById(id);
        User userDto = null;
        if(userById.isPresent()){
            User userBd = userById.orElseThrow();
           
            userBd.setRoles(getRoles(user));
            userBd.setUsername(user.getUsername());
            userBd.setName(user.getName());
            userBd.setLastname(user.getLastname());
            userDto = userRepository.save(userBd);
        }
        return Optional.ofNullable(DtoMapperUser.builder().setUser(userDto).build());
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existByUsername(String username) {
       return userRepository.existsByUsername(username);
    }

    private List<Role> getRoles(IUser user) {

        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        return roles;
    }

}
