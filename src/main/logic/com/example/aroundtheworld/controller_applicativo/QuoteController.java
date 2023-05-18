package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.CityBean;
import com.example.aroundtheworld.bean.QuoteBean;
import com.example.aroundtheworld.dao.CityDAO;
import com.example.aroundtheworld.engineering.decorator.*;
import com.example.aroundtheworld.engineering.decorator.decorations.*;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.City;

public class QuoteController {
    public CityBean getCityInfo(String cityName) throws NotFoundException {
        City city = CityDAO.retrieveCity(cityName);
        CityBean cityBean = new CityBean(city.getName());
        cityBean.setAct1Bean(city.getAct1());
        cityBean.setAct2Bean(city.getAct2());
        cityBean.setAct3Bean(city.getAct3());
        cityBean.setAct1ImgSrcBean(city.getAct1ImgSrc());
        cityBean.setAct2ImgSrcBean(city.getAct2ImgSrc());
        cityBean.setAct3ImgSrcBean(city.getAct3ImgSrc());
        return cityBean;
    }

    public int calculateQuote(QuoteBean quoteBean) {
        Quote quote = null;

        switch (quoteBean.getCity()){
            case "Rome" -> {
                quote = new RomeQuote();
                if(quoteBean.getActivity1() == 1){
                    quote = new ColosseumDecorator(quote);
                }
                if(quoteBean.getActivity2() == 1){
                    quote = new VaticanMuseumDecorator(quote);
                }
                if(quoteBean.getActivity3() == 1){
                    quote = new FoodDecorator(quote);
                }
            }
            case "New York" -> {
                quote = new NewYorkQuote();
                if(quoteBean.getActivity1() == 1){
                    quote = new TimeSquareDecorator(quote);
                }
                if(quoteBean.getActivity2() == 1){
                    quote = new EmpireDecorator(quote);
                }
                if(quoteBean.getActivity3() == 1){
                    quote = new CentralParkDecorator(quote);
                }
            }
            case "Paris" -> {
                quote = new ParisQuote();
                if(quoteBean.getActivity1() == 1){
                    quote = new LouvreDecorator(quote);
                }
                if(quoteBean.getActivity2() == 1){
                    quote = new DisneylandDecorator(quote);
                }
                if(quoteBean.getActivity3() == 1){
                    quote = new CruiseDecorator(quote);
                }
            }
            case "Valencia" -> {
                quote = new ValenciaQuote();
                if(quoteBean.getActivity1() == 1){
                    quote = new OceanographicDecorator(quote);
                }
                if(quoteBean.getActivity2() == 1){
                    quote = new ScienceMuseumDecorator(quote);
                }
                if(quoteBean.getActivity3() == 1){
                    quote = new MercadoCentralDecorator(quote);
                }
            }
            default -> {
                quote = new LondonQuote();
                if(quoteBean.getActivity1() == 1){
                    quote = new LondonEyeDecorator(quote);
                }
                if(quoteBean.getActivity2() == 1){
                    quote = new HPStudiosDecorator(quote);
                }
                if(quoteBean.getActivity3() == 1){
                    quote = new BuckinghamPalaceDecorator(quote);
                }
            }
        }

        quote = addStandardInformation(quoteBean, quote);

        return quote.getPrice() + (250 * quoteBean.getWeeks());

    }

    private Quote addStandardInformation(QuoteBean quoteBean, Quote quote) {
        if(quoteBean.getAccommodation().equals("host family")){
            quote = new HostFamilyDecorator(quote, quoteBean.getWeeks());
        } else {
            quote = new ResidenceDecorator(quote, quoteBean.getWeeks());
            if(quoteBean.getRoom().equals("single")){
                quote = new SingleRoomDecorator(quote, quoteBean.getWeeks());
            }
        }
        if(quoteBean.getSport() == 1){
            quote = new SportDecorator(quote, quoteBean.getWeeks());
        }
        return quote;
    }
}
