package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.validation.ExistByProductName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ExistByProductName
    @NotBlank
    @Size(min = 3, max = 25)
    private String name;

    @NotNull
    @Min(0)
    private Long precio;

    private boolean rebaja;

    private boolean disponible;


    @JsonIgnoreProperties({"products", "handler", "hibernateLazyInitializer"})
    @ManyToMany
    @JoinTable(
        name = "products_tallas",
        joinColumns = @JoinColumn(name="product_id"),
        inverseJoinColumns = @JoinColumn(name="talla_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "talla_id"})}
    )
    private List<Talla> tallas;

    @JsonIgnoreProperties({"products", "handler", "hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    @JsonIgnoreProperties({"products", "handler", "hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "tamanio_id")
    private Tamanio tamanio;

    @JsonIgnoreProperties({"products", "handler", "hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "products_images",
        joinColumns = @JoinColumn (name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "images_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"images_id"}))
    private List<Images> images;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "products_cantidad",
        joinColumns = @JoinColumn (name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "cantidad_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"cantidad_id"}))
    private List<Cantidad> cantidad;


    public Product() {
        this.tallas = new ArrayList<>();
        this.images = new ArrayList<>();
        this.cantidad = new ArrayList<>();
    }

    public Product(String name, Long precio, 
    Tipo tipo, Tamanio tamanio, Color color) {
        this();
        this.name = name;
        this.precio = precio;
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.color = color;
      
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


    public List<Talla> getTallas() {
        return tallas;
    }

    public void setTallas(List<Talla> tallas) {
        this.tallas = tallas;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tamanio getTamanio() {
        return tamanio;
    }

    public void setTamanio(Tamanio tamanio) {
        this.tamanio = tamanio;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> image) {
        this.images = image;
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

    public List<Cantidad> getCantidad() {
        return cantidad;
    }

    public void setCantidad(List<Cantidad> cantidad) {
        this.cantidad = cantidad;
    }
    
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((precio == null) ? 0 : precio.hashCode());
        result = prime * result + (rebaja ? 1231 : 1237);
        result = prime * result + (disponible ? 1231 : 1237);
        result = prime * result + ((tallas == null) ? 0 : tallas.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + ((tamanio == null) ? 0 : tamanio.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((images == null) ? 0 : images.hashCode());
        result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (precio == null) {
            if (other.precio != null)
                return false;
        } else if (!precio.equals(other.precio))
            return false;
        if (rebaja != other.rebaja)
            return false;
        if (disponible != other.disponible)
            return false;
        if (tallas == null) {
            if (other.tallas != null)
                return false;
        } else if (!tallas.equals(other.tallas))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        if (tamanio == null) {
            if (other.tamanio != null)
                return false;
        } else if (!tamanio.equals(other.tamanio))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (images == null) {
            if (other.images != null)
                return false;
        } else if (!images.equals(other.images))
            return false;
        if (cantidad == null) {
            if (other.cantidad != null)
                return false;
        } else if (!cantidad.equals(other.cantidad))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product {id=" + id + 
               ", name=" + name + 
               ", precio=" + precio + 
               ", rebaja=" + rebaja + 
               ", disponible=" + disponible + 
               ", tallas=" + tallas + 
               ", tipo=" + tipo + 
               ", tamanio=" + tamanio + 
               ", color=" + color + 
               ", images=" + images + 
               ", cantidad=" + cantidad + 
               ", isRebaja()=" + isRebaja() + 
               ", isDisponible()=" + isDisponible() + 
               "}";
    }

    
}
