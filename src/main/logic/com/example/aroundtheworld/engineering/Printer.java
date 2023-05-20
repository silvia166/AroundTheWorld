package com.example.aroundtheworld.engineering;


import java.sql.SQLException;
import java.util.logging.Logger;

public class Printer {
        static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        private Printer(){}
        public static void error(String error){
                log.info(error);
        }


}
