package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto;

public class DetailsDto {

    private String talla;

    private String tipo;

    private String tamanio;

    private String color;

    public DetailsDto() {
    }

    public DetailsDto(String talla, String color, String tipo, String tamanio) {
        this.talla = talla;
        this.color = color;
        this.tipo = tipo;
        this.tamanio = tamanio;
    }

    public DetailsDto(String color, String tipo, String tamanio) {
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.color = color;
    }

    public DetailsDto(String tipo, String tamanio) {
        this.tipo = tipo;
        this.tamanio = tamanio;
        
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
