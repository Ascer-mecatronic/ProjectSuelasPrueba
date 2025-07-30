package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.entities.pages;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pages")
public class Pages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(name = "image_one")
    private String imageOne;

    @Column(name = "image_two")
    private String imageTwo;

    @Column(name = "image_three")
    private String imageThree;

    @Column(name = "text_one")
    private String textOne;

    @Column(name = "text_two")
    private String textTwo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "pages_collectionone", joinColumns = @JoinColumn(name = "pages_id"), inverseJoinColumns = @JoinColumn(name = "collectionone_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "collectionone_id" }))
    private List<CollectionOne> collectionOne;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "pages_collectiontwo", joinColumns = @JoinColumn(name = "pages_id"), inverseJoinColumns = @JoinColumn(name = "collectiontwo_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "collectiontwo_id" }))
    private List<CollectionTwo> collectionTwo;

    public Pages() {
        this.collectionOne = new ArrayList<>();
        this.collectionTwo = new ArrayList<>();
    }

    public Pages(String name, String imageOne, String imageTwo, String imageThree, String textOne,
            String textTwo) {
        this();
        this.name = name;
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
        this.imageThree = imageThree;
        this.textOne = textOne;
        this.textTwo = textTwo;
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

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getImageThree() {
        return imageThree;
    }

    public void setImageThree(String imageThree) {
        this.imageThree = imageThree;
    }

    public String getTextOne() {
        return textOne;
    }

    public void setTextOne(String textOne) {
        this.textOne = textOne;
    }

    public String getTextTwo() {
        return textTwo;
    }

    public void setTextTwo(String textTwo) {
        this.textTwo = textTwo;
    }

    public List<CollectionOne> getCollectionOne() {
        return collectionOne;
    }

    public void setCollectionOne(List<CollectionOne> collectionOne) {
        this.collectionOne = collectionOne;
    }

    public List<CollectionTwo> getCollectionTwo() {
        return collectionTwo;
    }

    public void setCollectionTwo(List<CollectionTwo> collectionTwo) {
        this.collectionTwo = collectionTwo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((imageOne == null) ? 0 : imageOne.hashCode());
        result = prime * result + ((imageTwo == null) ? 0 : imageTwo.hashCode());
        result = prime * result + ((imageThree == null) ? 0 : imageThree.hashCode());
        result = prime * result + ((textOne == null) ? 0 : textOne.hashCode());
        result = prime * result + ((textTwo == null) ? 0 : textTwo.hashCode());
        result = prime * result + ((collectionOne == null) ? 0 : collectionOne.hashCode());
        result = prime * result + ((collectionTwo == null) ? 0 : collectionTwo.hashCode());
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
        Pages other = (Pages) obj;
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
        if (imageOne == null) {
            if (other.imageOne != null)
                return false;
        } else if (!imageOne.equals(other.imageOne))
            return false;
        if (imageTwo == null) {
            if (other.imageTwo != null)
                return false;
        } else if (!imageTwo.equals(other.imageTwo))
            return false;
        if (imageThree == null) {
            if (other.imageThree != null)
                return false;
        } else if (!imageThree.equals(other.imageThree))
            return false;
        if (textOne == null) {
            if (other.textOne != null)
                return false;
        } else if (!textOne.equals(other.textOne))
            return false;
        if (textTwo == null) {
            if (other.textTwo != null)
                return false;
        } else if (!textTwo.equals(other.textTwo))
            return false;
        if (collectionOne == null) {
            if (other.collectionOne != null)
                return false;
        } else if (!collectionOne.equals(other.collectionOne))
            return false;
        if (collectionTwo == null) {
            if (other.collectionTwo != null)
                return false;
        } else if (!collectionTwo.equals(other.collectionTwo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pages [id=" + id + ", name=" + name + ", imageOne=" + imageOne + ", imageTwo=" + imageTwo
                + ", imageThree=" + imageThree + ", textOne=" + textOne + ", textTwo=" + textTwo + ", collectionOne="
                + collectionOne + ", collectionTwo=" + collectionTwo + "]";
    }

}
