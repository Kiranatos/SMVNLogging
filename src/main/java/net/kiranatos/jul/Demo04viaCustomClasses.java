package net.kiranatos.jul;

import java.io.IOException;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class Demo04viaCustomClasses {
    final static Logger LOGGER = Logger.getLogger(Demo04viaCustomClasses.class.getName());
    
    public static void main(String[] args) {
        LOGGER.setLevel(Level.FINE);
        LOGGER.addHandler(new ConsoleHandler());
                
        LOGGER.addHandler(new MyHandler()); //adding custom handler
        System.out.println(LOGGER.getHandlers()[0].toString());
        System.out.println(LOGGER.getHandlers().length);
        try {
            //FileHandler file name with max size and number of log files limit:
            Handler fileHandler = new FileHandler("YouCanDeleteThisResultFileWithLogs.log", 2000, 5);            
            fileHandler.setFormatter(new MyFormatter());
            
            Filter filter = new MyFilter(); //don't log CONFIG logs in file
            fileHandler.setFilter(filter); //setting custom filter for FileHandler
            LOGGER.addHandler(fileHandler);
            
            for(int i = 0; i < 1000; i++){ //logging messages                
                LOGGER.log(Level.INFO, "Message" + i);
            }
            LOGGER.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) { }
    }    
}

class MyHandler extends StreamHandler /*SocketHandler */ {
    @Override //add own logic to .publish method:
    public void publish(LogRecord record) { super.publish(record); }

    @Override
    public void flush() { super.flush(); }

    @Override
    public void close() throws SecurityException {  super.close();  }
}

class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record.getThreadID() + "::" + record.getSourceClassName() + "::"
                + record.getSourceMethodName() + "::"
                + new Date(record.getMillis()) + "::"
                + record.getMessage() + "\n";
    }
}

class MyFilter implements Filter {
    public boolean isLoggable(LogRecord log) {
        if(log.getLevel() == Level.CONFIG) return false;
        return true;
    }
}