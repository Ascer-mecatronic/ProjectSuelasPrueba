package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.springframework.web.multipart.MultipartFile;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.CantidadDto;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.dto.CantidadList;

//import com.google.gson.Gson;
//import com.google.gson.JsonArray;

//import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.validation.ExistByProductName;

import jakarta.validation.constraints.Size;

public class ProductRequest{

    private Long id;

    @Size(min=4, max = 20)
    private String name;

    private Long precio;

    private boolean rebaja;

    private boolean disponible;

    private String tipo;
    
    private String tamanio;
    
    private String color;
    
    private List<MultipartFile> files;
    
    private String [] tallas;

    private String [] cantidad;

    //private List<Integer> cantidadList;

    //private CantidadDto cantidad;


    public ProductRequest() {
        
        this.files = new ArrayList<>();
        
        //this.cantidad = new ArrayList<>();
        this.precio = 0L;
    }

    
    public ProductRequest(Long id, String name, Long precio,
     String tipo, String tamanio, String [] tallas, String color, String[] cantidad) {
        this();
        this.id = id;
        this.name = name;
        this.precio = precio;
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.tallas = tallas;
        this.color = color;
        this.cantidad = cantidad;
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public boolean isRebaja() {
        return rebaja;
    }

    public void setRebaja(boolean rebaja) {
        this.rebaja = rebaja;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipo() {
        return this.tipo;
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

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
    

    public List<String> getTallaList() {
        return Arrays.asList(this.getTallas());
    }

    public String[] getTallas() {
        return tallas;
    }

    public void setTallas(String [] tallas) {
        this.tallas = tallas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

  


    /*public List<CantidadDto> getListCantidad(){
        JsonArray jsonArray =  new JsonArray();
        for (String jsonElement : this.getCantidad()) {
            jsonArray.add(jsonElement);
        }
        int x = 0;
        for (int i = 0; i <= this.getCantidad().length; i++) {
            CantidadDto cdto = new CantidadDto();
            cdto.setName(this.getCantidad()[i]);
            
        }
        Gson gson = new Gson();
        CantidadDto[] cantidadDto = gson.fromJson(jsonArray, CantidadDto[].class);
        return Arrays.asList(cantidadDto);
    }*/

    
    
    
    
    public String[] getCantidad() {
        return cantidad;
    }
    
    
    public void setCantidad(String[] cantidad) {
        this.cantidad = cantidad;
    }
    
    public List<String> getListCantidad(){
        return Arrays.asList(this.getCantidad());
    }

    public List<CantidadDto> getListCantidadDto(){

        List<CantidadDto> cantDto = new ArrayList<>();
        int [] num = new int[getCantidad().length];
        for (int i = 0; i < getCantidad().length; i++) {
            num[i] = Integer.parseInt(getCantidad()[i]);
        }
        /*List<Integer> lista = getListCantidad().stream().map(li ->{
            int number = Integer.parseInt(li);
            return number;
        }).collect(Collectors.toList());*/
        //List<Integer> listCant = lista.collect(Collectors.toList());
        CantidadList[] cLists = CantidadList.values();
        for (int i = 0; i < cLists.length; i++) {
            CantidadDto cDto = new CantidadDto(cLists[i].numero,num[i]);
            cantDto.add(cDto);
        }
        return cantDto;
    }

    @Override
    public String toString() {
        return "{name=" + name +
                ", precio=" + precio +
                ", tallas=" + tallas +
                ", tipo=" + tipo +
                ", tamanio=" + tamanio +
                ", colores=" + color+
                ", files=" + files + "}";
    }


}
