package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.IUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRequest implements IUser{

    @NotBlank
    @Size(min= 4, max = 25)
    private String username;

    @NotEmpty
    @Size(min= 4, max = 12)
    private String name;

    @NotEmpty
    @Size(min= 4, max = 12)
    private String lastname;

    private boolean admin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    } 

}
