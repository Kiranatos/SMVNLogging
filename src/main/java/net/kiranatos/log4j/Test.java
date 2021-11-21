package net.kiranatos.log4j;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;

/*
https://habr.com/ru/post/247647/
https://www.codeflow.site/ru/article/logging__log4j-xml-example
https://logging.apache.org/log4j/2.x/manual/layouts.html#
*/

public class Test {
    
    final static Logger LOGGER = LogManager.getLogger(Test.class);
    /* must be: 
     - static
     - one per project. Can be final but it's not necessary since it is static and initiate one time.
     - possible names: log, logger, LOG, LOGGER
     - never initiate inside methods, constructors and others  */
    
    public static void main(String[] args) {
        System.out.println(LOGGER.getLevel());  
        demonstarteAllLevels();
        
        
        
        
    }
    
    static void demonstarteAllLevels(){        
        LOGGER.trace("TRACE Level via appropriate method");
        LOGGER.log(Level.TRACE, "TRACE Level via log method");
        
        LOGGER.debug("DEBUG Level via appropriate method");
        LOGGER.log(Level.DEBUG, "DEBUG Level via log method");
        
        LOGGER.info("INFO Level via appropriate method");
        LOGGER.log(Level.INFO, "INFO Level via log method");
        
        LOGGER.warn("WARN Level via appropriate method");
        LOGGER.log(Level.WARN, "WARN Level via log method");
        
        LOGGER.error("ERROR Level via appropriate method");
        LOGGER.log(Level.ERROR, "ERROR Level via log method");
        
        LOGGER.fatal("FATAL Level via appropriate method");
        LOGGER.log(Level.FATAL, "FATAL Level via log method");
    }
}
/*

*/