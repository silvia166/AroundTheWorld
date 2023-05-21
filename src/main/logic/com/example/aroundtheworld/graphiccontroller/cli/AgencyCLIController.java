package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.engineering.Printer;

public class AgencyCLIController implements GraphicCLIController {
    @Override
    public void start() {
        Printer.printMessage("Agency");
    }
}
