package com.example.aroundtheworld.model;

public class City {
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

    public City(String name, String language, String act1, String act2, String act3, Residence residence, School school) {
        this.name = name;
        this.language = language;
        this.act1 = act1;
        this.act2 = act2;
        this.act3 = act3;
        this.residence = residence;
        this.school = school;
    }

    public void setCityImgSrc(String cityImgSrc) {
        this.cityImgSrc = cityImgSrc;
    }

    public void setAct1ImgSrc(String act1ImgSrc) {
        this.act1ImgSrc = act1ImgSrc;
    }

    public void setAct2ImgSrc(String act2ImgSrc) {
        this.act2ImgSrc = act2ImgSrc;
    }

    public void setAct3ImgSrc(String act3ImgSrc) {
        this.act3ImgSrc = act3ImgSrc;
    }

    public String getName() {
        return name;
    }

    public String getCityImgSrc() {
        return cityImgSrc;
    }

    public String getLanguage() {
        return language;
    }

    public String getAct1() {
        return act1;
    }

    public String getAct1ImgSrc() {
        return act1ImgSrc;
    }

    public String getAct2() {
        return act2;
    }

    public String getAct2ImgSrc() {
        return act2ImgSrc;
    }

    public String getAct3() {
        return act3;
    }

    public String getAct3ImgSrc() {
        return act3ImgSrc;
    }

    public Residence getResidence() {
        return residence;
    }

    public School getSchool() {
        return school;
    }
}
