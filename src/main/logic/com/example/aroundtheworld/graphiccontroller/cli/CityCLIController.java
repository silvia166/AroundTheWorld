package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.CityController;
import com.example.aroundtheworld.bean.CityBean;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.exception.NotImplementedException;
import com.example.aroundtheworld.viewcli.CityViewCLI;

public class CityCLIController implements GraphicCLIController{

    private static final String LONDON = "1";
    private static final String ROME = "2";
    private static final String PARIS = "3";
    private static final String NEW_YORK = "4";
    private static final String VALENCIA = "5";
    private static final String TEMPERATURE = "1";
    private static final String BACK = "2";
    private CityViewCLI cityViewCLI;
    @Override
    public void start() {
        this.cityViewCLI = new CityViewCLI(this);
        this.cityViewCLI.run();
    }
    public void getCity(String inputLine) throws CommandErrorException, NotFoundException {
        String name;
        switch (inputLine){
            case ROME -> name = "Rome";
            case LONDON -> name = "London";
            case VALENCIA -> name = "Valencia";
            case PARIS -> name = "Paris";
            case NEW_YORK -> name = "New York";
            default -> throw new CommandErrorException();
        }
        CityBean cityBean = new CityBean(name);
        CityController cityController = new CityController();
        cityController.setCity(cityBean);

        cityViewCLI.setCityInfo(cityBean.getNameBean(),cityBean.getLanguageBean(),cityBean.getAct1Bean(),cityBean.getAct2Bean(),cityBean.getAct3Bean());
        cityViewCLI.setResidenceInfo(cityBean.getResidenceBean().getName(), cityBean.getResidenceBean().getAddress(), cityBean.getResidenceBean().getDistanceSchool());
        cityViewCLI.setSchoolInfo(cityBean.getSchoolBean().getName(),cityBean.getSchoolBean().getAddress(),cityBean.getSchoolBean().getHours(),cityBean.getSchoolBean().getCourses());
    }

    public void executeCommand(String nextLine) throws CommandErrorException, NotImplementedException {
        switch (nextLine){
            case TEMPERATURE -> throw new NotImplementedException();
            case BACK -> {
                LoginCLIController loginCLIController = new LoginCLIController();
                loginCLIController.start();
            }
            default -> throw new CommandErrorException();
        }
    }
}
