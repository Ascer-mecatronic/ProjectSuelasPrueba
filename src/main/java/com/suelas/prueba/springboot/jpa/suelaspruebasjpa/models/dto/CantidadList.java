package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto;

public enum CantidadList {

    DIECINUEVE("19"),
    VEINTE("20"),
    VEINTIUNO("21"),
    VEINTIDOS("22"),
    VEINTITRES("23"),
    VEINTICUATRO("24"),
    VEINTICINCO("25"),
    VEINTISEIS("26"),
    VEINTISIETE("27"),
    VEINTIOCHO("28"),
    VEINTINUEVE("29");

    public final String numero;

    private CantidadList(String numero) {
        this.numero = numero;
    }

    

}
