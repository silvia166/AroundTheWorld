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

    public String getNameBean() {
        return cityName;
    }

    public String getCityImgSrcBean() {
        return cityImg;
    }

    public void setCityImgSrcBean(String cityImgSrc) {
        this.cityImg = cityImgSrc;
    }

    public String getLanguageBean() {
        return cityLanguage;
    }

    public void setLanguageBean(String language) {
        this.cityLanguage = language;
    }

    public String getAct1Bean() {
        return activity1;
    }

    public void setAct1Bean(String act1) {
        this.activity1 = act1;
    }

    public String getAct1ImgSrcBean() {
        return act1Img;
    }

    public void setAct1ImgSrcBean(String act1ImgSrc) {
        this.act1Img = act1ImgSrc;
    }

    public String getAct2Bean() {
        return activity2;
    }

    public void setAct2Bean(String act2) {
        this.activity2 = act2;
    }

    public String getAct2ImgSrcBean() {
        return act2Img;
    }

    public void setAct2ImgSrcBean(String act2ImgSrc) {
        this.act2Img = act2ImgSrc;
    }

    public String getAct3Bean() {
        return activity3;
    }

    public void setAct3Bean(String act3) {
        this.activity3 = act3;
    }

    public String getAct3ImgSrcBean() {
        return act3Img;
    }

    public void setAct3ImgSrcBean(String act3ImgSrc) {
        this.act3Img = act3ImgSrc;
    }

    public Residence getResidenceBean() {
        return cityResidence;
    }

    public void setResidenceBean(Residence residence) {
        this.cityResidence = residence;
    }

    public School getSchoolBean() {
        return citySchool;
    }

    public void setSchoolBean(School school) {
        this.citySchool = school;
    }
}
