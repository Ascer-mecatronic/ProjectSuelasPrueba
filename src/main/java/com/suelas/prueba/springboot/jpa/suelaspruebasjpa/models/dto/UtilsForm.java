package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto;

import java.util.ArrayList;
import java.util.List;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Color;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Talla;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tamanio;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.Tipo;

public class UtilsForm {

    private List<Talla> tallas;

    private List<Color> colores;

    private List<Tamanio> tamanios;

    private List<Tipo> tipos;

    public UtilsForm() {
        this.tallas = new ArrayList<>();
        this.colores = new ArrayList<>();
        this.tamanios = new ArrayList<>();
        this.tipos = new ArrayList<>();
    }

    public List<Talla> getTallas() {
        return tallas;
    }

    public void setTallas(List<Talla> tallas) {
        this.tallas = tallas;
    }

    public List<Color> getColores() {
        return colores;
    }

    public void setColores(List<Color> colores) {
        this.colores = colores;
    }

    public List<Tamanio> getTamanios() {
        return tamanios;
    }

    public void setTamanios(List<Tamanio> tamanios) {
        this.tamanios = tamanios;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

}
