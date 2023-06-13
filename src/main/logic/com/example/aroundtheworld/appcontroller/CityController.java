package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.CityBean;
import com.example.aroundtheworld.dao.CityDAO;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.City;

public class CityController {
    public void setCity(CityBean cityBean) throws NotFoundException {
        City city = CityDAO.retrieveCity(cityBean.getName());
        cityBean.setLanguage(city.getLanguage());
        cityBean.setCityImgSrc(city.getCityImgSrc());
        cityBean.setAct1(city.getAct1());
        cityBean.setAct1ImgSrc(city.getAct1ImgSrc());
        cityBean.setAct2(city.getAct2());
        cityBean.setAct2ImgSrc(city.getAct2ImgSrc());
        cityBean.setAct3(city.getAct3());
        cityBean.setAct3ImgSrc(city.getAct3ImgSrc());
        cityBean.setResidence(city.getResidence().getAddress(), city.getResidence().getDistanceSchool());
        cityBean.setSchool(city.getSchool().getAddress(), city.getSchool().getHours(), city.getSchool().getCourses());
    }
}
