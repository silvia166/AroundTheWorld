package com.example.aroundtheworld.bean;

import com.example.aroundtheworld.model.Residence;
import com.example.aroundtheworld.model.School;

public class CityBean {
    private String name;
    private String cityImgSrc;
    private String language;
    private String act1;
    private String act1ImgSrc;
    private String act2;
    private String act2ImgSrc;
    private String act3;
    private String act3ImgSrc;
    private Residence residence;
    private School school;

    public CityBean(String name) {
        this.name = name;
    }

    public String getNameBean() {
        return name;
    }

    public String getCityImgSrcBean() {
        return cityImgSrc;
    }

    public void setCityImgSrc(String cityImgSrc) {
        this.cityImgSrc = cityImgSrc;
    }

    public String getLanguageBean() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAct1Bean() {
        return act1;
    }

    public void setAct1(String act1) {
        this.act1 = act1;
    }

    public String getAct1ImgSrcBean() {
        return act1ImgSrc;
    }

    public void setAct1ImgSrc(String act1ImgSrc) {
        this.act1ImgSrc = act1ImgSrc;
    }

    public String getAct2Bean() {
        return act2;
    }

    public void setAct2(String act2) {
        this.act2 = act2;
    }

    public String getAct2ImgSrcBean() {
        return act2ImgSrc;
    }

    public void setAct2ImgSrc(String act2ImgSrc) {
        this.act2ImgSrc = act2ImgSrc;
    }

    public String getAct3Bean() {
        return act3;
    }

    public void setAct3(String act3) {
        this.act3 = act3;
    }

    public String getAct3ImgSrcBean() {
        return act3ImgSrc;
    }

    public void setAct3ImgSrc(String act3ImgSrc) {
        this.act3ImgSrc = act3ImgSrc;
    }

    public Residence getResidenceBean() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public School getSchoolBean() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
