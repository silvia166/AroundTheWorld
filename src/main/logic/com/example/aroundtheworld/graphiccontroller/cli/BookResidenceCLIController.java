package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.BookingResidenceController;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.MessageException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.viewcli.BookingFormViewCLI;

public class BookResidenceCLIController implements GraphicCLIController {

    BookingFormViewCLI bookingFormViewCLI;

    @Override
    public void start() {
        this.bookingFormViewCLI = new BookingFormViewCLI(this);
        this.bookingFormViewCLI.run();
    }

    public void sendResidenceRequest(String city, String arrival, String departure, String room) {
        try {
            int idStudent = Session.getCurrentSession().getStudentBean().getId();
            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(city, arrival, departure, room, idStudent, 0);
            BookingResidenceController bookingResidenceController = new BookingResidenceController();
            bookingResidenceController.sendRequest(residenceRequestBean);
            bookingFormViewCLI.displayRequestSentMessage();
        }
        catch (MessageException | NotFoundException | DuplicateRequestException e){
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

}
