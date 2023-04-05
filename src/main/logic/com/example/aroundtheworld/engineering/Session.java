package com.example.aroundtheworld.engineering;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.StudentBean;

public class Session {

    private static Session sessionInstance = null;

    private StudentBean studentBean;
    private FamilyBean familyBean;

    private Session(Object ob) {

        if(ob instanceof StudentBean){
            this.studentBean = (StudentBean) ob;
        }
        else if(ob instanceof FamilyBean){
            familyBean = (FamilyBean) ob;
        }
    }

    public static void setSessionInstance(Object ob) {
        if(sessionInstance == null)
            sessionInstance = new Session(ob);
    }



    public static void closeSession() {
        sessionInstance = null;
    }

    public static Session getCurrentSession() {
        return sessionInstance;
    }

    public StudentBean getStudentBean() {
        return studentBean;
    }

    public FamilyBean getFamilyBean() {
        return familyBean;
    }

}
