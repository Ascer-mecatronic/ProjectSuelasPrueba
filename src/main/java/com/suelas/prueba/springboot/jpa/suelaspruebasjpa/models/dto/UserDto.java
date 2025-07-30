package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto;

public class UserDto {

    private Long id;

    private String username;

    private String name;

    private String lastname;

    private boolean admin;

    public UserDto() {
    }

    public UserDto(Long id, String username, String name, String lastname, boolean admin) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
