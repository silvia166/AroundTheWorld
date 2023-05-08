package com.example.aroundtheworld.bean;

import java.util.List;

public class CompatibleFamilyBean {

    private int idFamily;
    private String name;
    private String imgSrc;
    private float compatibility;
    
    public CompatibleFamilyBean(int idFamily, String name, String imgSrc, float compatibility) {
        this.idFamily = idFamily;
        this.name = name;
        this.imgSrc = imgSrc;
        this.compatibility = compatibility;
    }

    public int getId() {
        return idFamily;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public float getCompatibility() {
        return compatibility;
    }

    public String getEmail(){
        String mail = name.toLowerCase();
        mail = mail.concat("@gmail.com");
        return mail;
    }
}
