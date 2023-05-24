package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.QuoteController;
import com.example.aroundtheworld.bean.CityBean;
import com.example.aroundtheworld.bean.QuoteBean;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.viewcli.QuoteViewCLI;

public class QuoteCLIController implements GraphicCLIController{
    private QuoteViewCLI quoteViewCLI;
    private QuoteBean quoteBean;

    @Override
    public void start() {
        this.quoteViewCLI = new QuoteViewCLI(this);
        this.quoteViewCLI.run();

    }
    public void getCityActivities(String city, int permanence, int quantity, String accommodation, String room) throws NotFoundException {
        if(permanence == 2){
            quantity = quantity * 4;
        }else if(permanence == 3){
            quantity = quantity * 52;
        }
        this.quoteBean = new QuoteBean(city, quantity, accommodation, room);
        QuoteController quoteController = new QuoteController();
        CityBean cityBean = quoteController.getCityInfo(city);
        quoteViewCLI.showActivities(cityBean.getAct1Bean(), cityBean.getAct2Bean(), cityBean.getAct3Bean());
    }

    public void setActivities(int act1, int act2, int act3, int sport) {
        this.quoteBean.setActivity1(act1);
        this.quoteBean.setActivity2(act2);
        this.quoteBean.setActivity3(act3);
        this.quoteBean.setSport(sport);
        QuoteController quoteController = new QuoteController();
        int price = quoteController.calculateQuote(this.quoteBean);
        quoteViewCLI.showQuote(price);
    }
}
