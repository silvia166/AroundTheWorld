package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.CityBean;
import com.example.aroundtheworld.dao.CityDAO;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.City;

public class CityController {
    public void setCity(CityBean cityBean) throws NotFoundException {
        City city = CityDAO.retrieveCity(cityBean.getNameBean());
        cityBean.setLanguageBean(city.getLanguage());
        cityBean.setCityImgSrcBean(city.getCityImgSrc());
        cityBean.setAct1Bean(city.getAct1());
        cityBean.setAct1ImgSrcBean(city.getAct1ImgSrc());
        cityBean.setAct2Bean(city.getAct2());
        cityBean.setAct2ImgSrcBean(city.getAct2ImgSrc());
        cityBean.setAct3Bean(city.getAct3());
        cityBean.setAct3ImgSrcBean(city.getAct3ImgSrc());
        cityBean.setResidenceBean(city.getResidence());
        cityBean.setSchoolBean(city.getSchool());
    }
}
