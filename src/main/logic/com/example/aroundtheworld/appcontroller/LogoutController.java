package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.engineering.Session;

public class LogoutController {

    public void logout()  {
        Session.closeSession();
    }
}
