package net.kiranatos.lang;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ResourceBundle;

public class Demo01JavaLangSystemLogger {
    static Logger logg_2 = System.getLogger(Demo01JavaLangSystemLogger.class.getName());
    
    Logger logg_1 = new Logger() {
            @Override
            public String getName() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isLoggable(Level level) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void log(Level level, ResourceBundle bundle, String msg, Throwable thrown) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void log(Level level, ResourceBundle bundle, String format, Object... params) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    
    public static void main(String[] args) {
        logg_2.log(Level.ALL, "");
        logg_2.log(Level.DEBUG, "");
        logg_2.log(Level.ERROR, "");
        logg_2.log(Level.INFO, "");
        logg_2.log(Level.OFF, "");
        logg_2.log(Level.TRACE, "");
        logg_2.log(Level.WARNING, "");
    }   
}