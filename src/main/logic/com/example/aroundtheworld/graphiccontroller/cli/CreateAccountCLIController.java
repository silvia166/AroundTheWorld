package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.CreateAccountController;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.EmailFormatException;
import com.example.aroundtheworld.exception.MessageException;
import com.example.aroundtheworld.exception.PhoneFormatException;
import com.example.aroundtheworld.viewcli.AccountFormViewCLI;

public class CreateAccountCLIController implements GraphicCLIController{

    AccountFormViewCLI accountFormViewCLI;
    @Override
    public void start() {
        this.accountFormViewCLI = new AccountFormViewCLI(this);
        this.accountFormViewCLI.run();
    }

    public void createAccount(String name, String surname, String nationality, String birth, String phone, String email, String password) {
        try {
            StudentBean studentBean = new StudentBean(name,surname,nationality,birth,email,phone,password);
            CreateAccountController createAccountController = new CreateAccountController();
            createAccountController.createAccount(studentBean);
        } catch (EmailFormatException | PhoneFormatException | MessageException e){
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }
}
