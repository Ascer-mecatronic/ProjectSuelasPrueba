package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.models.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PagesRequest {

    private Long id;

    private String name;

    private MultipartFile imageOne;

    private MultipartFile imageTwo;

    private MultipartFile imageThree;

    private String textOne;

    private String textTwo;

    private List<MultipartFile> filesOne;

    private List<MultipartFile> filesTwo;

    public PagesRequest() {
        this.filesOne = new ArrayList<>();
        this.filesTwo = new ArrayList<>();
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

    public MultipartFile getImageOne() {
        return imageOne;
    }

    public void setImageOne(MultipartFile imageOne) {
        this.imageOne = imageOne;
    }

    public MultipartFile getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(MultipartFile imageTwo) {
        this.imageTwo = imageTwo;
    }

    public MultipartFile getImageThree() {
        return imageThree;
    }

    public void setImageThree(MultipartFile imageThree) {
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

    public List<MultipartFile> getFilesOne() {
        return filesOne;
    }

    public void setFilesOne(List<MultipartFile> filesOne) {
        this.filesOne = filesOne;
    }

    public List<MultipartFile> getFilesTwo() {
        return filesTwo;
    }

    public void setFilesTwo(List<MultipartFile> filesTwo) {
        this.filesTwo = filesTwo;
    }

}
