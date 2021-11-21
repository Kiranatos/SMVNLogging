package net.kiranatos.jul;

import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.kiranatos.OzoHelper;

public class Demo01Logging {
    /*важно чтобы во всех Ваших классах переменная логера называлась одинаково.
    можно использовать и прописываnm полный путь к классу "com.dataart.SomeClass", 
    но тот что ниже защищает от сюрпризов при рефакторинге имени/пакета класса.
    По сути это инстанс доступа к механизму что-то вроде сеттера, а не сам логгер */
    static final Logger LOGGER1 = Logger.getLogger(Demo01Logging.class.getName()); // Имя класса передается для того, чтобы знать, откуда идет логирование.
    //static final Logger LOGGER2 = Logger.getAnonymousLogger(Demo01Logging.class.getName());
    //static final Logger LOGGER3 = Logger.getGlobal();
    //Logger LOGGER4 = Logger.getLogger("logfile.txt");
        
    public static void main(String[] args) {        
        System.out.println("\tExample 1:");
        System.out.println("\tLOGGER level is: " + LOGGER1.getLevel());
        LOGGER1.setLevel(Level.WARNING); /* Журналы регистрируют сообщение только. Они не публикуют сообщения в пункты назначения, 
        о которых заботятся обработчики. Установка уровня регистратора приводит к тому, что он создает записи журнала, 
        соответствующие этому уровню и выше.
            В Java\jdk-13.0.1\conf\logging.properties есть ConsoleHandler by default.*/
        LOGGER1.setUseParentHandlers(false); // Чтобы не был двойной вывод - выключаем родительский
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.CONFIG);
        LOGGER1.addHandler(handler);        
        demonstarteAllLevels(LOGGER1);
        /*Output depends from both levels of Loger and Handler */
        System.out.println("\tLOGGER level is: " + LOGGER1.getLevel());
        System.out.println("\tConsoleHandler level is: " + handler.getLevel());
        System.out.println("\tTotal handlers: " + LOGGER1.getHandlers().length);
        
        System.out.println("\n\tExample 2:");
        demonstrateOutputs(LOGGER1);
    }
    
    static void demonstarteAllLevels(Logger log){
        log.finest("FINEST level via appropriate method");
        log.log(Level.FINEST, "FINEST level via log method");
        
        log.finer("FINER level via appropriate method");
        log.log(Level.FINER, "FINER level via log method");
        
        log.fine("FINE level via appropriate method");
        log.log(Level.FINE, "FINE level via log method");
        
        log.config("CONFIG level via appropriate method");
        log.log(Level.CONFIG, "CONFIG level via log method");
        
        log.info("INFO level via appropriate method");
        log.log(Level.INFO, "INFO level via log method");
        
        log.warning("WARNING level via appropriate method");
        log.log(Level.WARNING, "WARNING level via log method");
        
        log.severe("SEVERE level via appropriate method");
        log.log(Level.SEVERE, "SEVERE level via log method");
    }
    
    static void demonstrateOutputs(Logger log) {
        /* Вывод логов может требовать больших ресурсов (напр., дамп пакета данных).
        Иногда стоит проверить выведется ли лог этого уровня */
        if (log.isLoggable(Level.WARNING)) {
            log.log(Level.WARNING, "Example of output 1: {0}", Arrays.toString(new int[]{10,25,35}));
            
            String[] s = {"string one", "string two"};
            log.log(Level.WARNING, "Example of output 2: {0} {1}", s);            
        }        
        
        try {
            double randomNumber = Math.random();
            log.log(Level.WARNING, "Generated Random Number: {0}", randomNumber);
            if (randomNumber < 0.5) {
                throw new IllegalStateException("Invalid phase of the Moon");
            }
        } catch (IllegalStateException ex) {
            //Если логировать только e.toString(), то потом Вы не сможете понять в какой строке изначально сработало исключение.
            Logger.getLogger(Demo01Logging.class.getName()).log(Level.SEVERE, "Exception caught version ONE", ex); // или:
            LOGGER1.log(Level.SEVERE, "Exception caught version TWO", ex);
            //System.exit(2);
        }
    }    
}
