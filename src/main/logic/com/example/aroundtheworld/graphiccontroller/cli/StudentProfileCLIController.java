package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.viewcli.StudentProfileViewCLI;

public class StudentProfileCLIController implements GraphicCLIController{
    private StudentProfileViewCLI studentProfileViewCLI;

    @Override
    public void start() {
        this.studentProfileViewCLI = new StudentProfileViewCLI(this);
        StudentBean studentBean = Session.getCurrentSession().getStudentBean();
        studentProfileViewCLI.run(studentBean.getName(), studentBean.getSurname(), studentBean.getNationality(), studentBean.getPhoneNumber(), studentBean.getEmail(), studentBean.getBirth());
    }
}
