package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.TravelBean;
import com.example.aroundtheworld.dao.FamilyRequestDAO;

public class RateController {
    public void rateFamily(TravelBean travelBean) {

        FamilyRequestDAO.updateRate(travelBean.getRate(), travelBean.getId());
    }
}
