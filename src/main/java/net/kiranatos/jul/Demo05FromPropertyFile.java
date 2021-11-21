package net.kiranatos.jul;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class Demo05FromPropertyFile {
    static Logger LOGGER = Logger.getLogger(Demo05FromPropertyFile.class.getName());
    
    public static void main(String[] args) {
        LogManager logManager = LogManager.getLogManager();
        try {
            logManager.readConfiguration(new FileInputStream("jul.properties"));
        } catch (SecurityException | IOException ex) { }
        LOGGER = logManager.getLogger(Demo05FromPropertyFile.class.getName());
        for(int i = 0; i < 10; i++){
            LOGGER.log(Level.INFO, "Message_" + i);
        }
        LOGGER.log(Level.CONFIG, "Config data");
        
        System.out.println(" loger name = " + LOGGER.getName());
        System.out.println(" nums of handlers = " + LOGGER.getHandlers().length);
        System.out.println(" level = " + LOGGER.getLevel());
    }    
}