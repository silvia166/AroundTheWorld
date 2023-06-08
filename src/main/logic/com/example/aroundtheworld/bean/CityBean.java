package com.example.aroundtheworld.bean;

import com.example.aroundtheworld.model.Residence;
import com.example.aroundtheworld.model.School;

public class CityBean {
    private String cityName;
    private String cityImg;
    private String cityLanguage;
    private String activity1;
    private String act1Img;
    private String activity2;
    private String act2Img;
    private String activity3;
    private String act3Img;
    private Residence cityResidence;
    private School citySchool;

    public CityBean(String name) {
        this.cityName = name;
    }

    public String getName() {
        return cityName;
    }

    public String getCityImgSrc() {
        return cityImg;
    }

    public void setCityImgSrc(String cityImgSrc) {
        this.cityImg = cityImgSrc;
    }

    public String getLanguage() {
        return cityLanguage;
    }

    public void setLanguage(String language) {
        this.cityLanguage = language;
    }

    public String getAct1() {
        return activity1;
    }

    public void setAct1(String act1) {
        this.activity1 = act1;
    }

    public String getAct1ImgSrc() {
        return act1Img;
    }

    public void setAct1ImgSrc(String act1ImgSrc) {
        this.act1Img = act1ImgSrc;
    }

    public String getAct2() {
        return activity2;
    }

    public void setAct2(String act2) {
        this.activity2 = act2;
    }

    public String getAct2ImgSrc() {
        return act2Img;
    }

    public void setAct2ImgSrc(String act2ImgSrc) {
        this.act2Img = act2ImgSrc;
    }

    public String getAct3() {
        return activity3;
    }

    public void setAct3(String act3) {
        this.activity3 = act3;
    }

    public String getAct3ImgSrc() {
        return act3Img;
    }

    public void setAct3ImgSrc(String act3ImgSrc) {
        this.act3Img = act3ImgSrc;
    }

    public Residence getResidence() {
        return cityResidence;
    }

    public void setResidence(Residence residence) {
        this.cityResidence = residence;
    }

    public School getSchool() {
        return citySchool;
    }

    public void setSchool(School school) {
        this.citySchool = school;
    }
}
