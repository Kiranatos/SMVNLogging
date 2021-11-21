package net.kiranatos.jul;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class Demo03Formatter {
    static final Logger LOGGER = Logger.getLogger(Demo03Formatter.class.getName());
        
    public static void main(String[] args) {    
        LOGGER.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new MyLogFormatter();
        handler.setFormatter(formatter);        
        LOGGER.addHandler(handler);
        
        LOGGER.severe("log's text in .severe method");
        LOGGER.log(Level.WARNING, "log's text in .log method", new String[]{"param1","20"});        
    }    
}

class MyLogFormatter extends Formatter {
    // ANSI escape code (do not work in Maven)
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";
    
    @Override
    public String format(LogRecord record) { // format is called for every console log message        
        StringBuilder builder = new StringBuilder();
        builder.append(ANSI_YELLOW);
        builder.append("[").append(calcDate(record.getMillis())).append("]");
        builder.append("[").append(record.getSourceClassName()).append("]");
        builder.append("[").append(record.getLevel().getName()).append("]");
        builder.append(ANSI_WHITE);
        builder.append("-").append(record.getMessage());

        Object[] params = record.getParameters();
        if (params != null) {
            builder.append("(");
            for (int i = 0; i < params.length; i++) {
                builder.append(params[i]);
                if (i < params.length - 1)
                    builder.append(", ");
            }
            builder.append(")");
        }

        builder.append(ANSI_RESET).append("\n");        
        return builder.toString();
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }
}