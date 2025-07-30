package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto;

public class CantidadDto {

    private String name;

    private int total;

    public CantidadDto() {
        this.total = 0;
    }

    public CantidadDto(String name, int total) {
        
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
