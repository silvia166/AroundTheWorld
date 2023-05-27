package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.dao.FamilyRequestDAO;

public class RateController {
    public void rateFamily(int rate, int idRequest) {
        FamilyRequestDAO.updateRate(rate, idRequest);
    }
}
