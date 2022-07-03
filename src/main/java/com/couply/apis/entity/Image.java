package com.couply.apis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {
    @Id
    private Integer id;
    private byte[] image;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Image() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image(byte[] image, String name, int id) {
        this.image = image;
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }
}
