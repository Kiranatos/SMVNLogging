package net.kiranatos.jul;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Demo02FileHandler {
    static final Logger LOGGER = Logger.getLogger(Demo02FileHandler.class.getName());
    public static void main(String[] args) {            
        try {
            //1-й файл: Символ "%t" указывает, что файл будет создан в temp-папке
            FileHandler fh = new FileHandler("%tLogApp.txt"); // C:\Documents and Settings\Kiranatos\Local Settings\Temp или C:\Users\Kiranatos\AppData\Local\Temp
            LOGGER.addHandler(fh);            
            // 2-й файл            
            FileHandler htmlfile = new FileHandler("LogApp.htm");
            LOGGER.addHandler(htmlfile);
            
            // html-форматирование для обох классов:
            Formatter htmlformatter = new MyHtmlFormatter();
            htmlfile.setFormatter(htmlformatter);
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, "Не удалось создать файл лога из-за политики безопасности.", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Не удалось создать файл лога из-за ошибки ввода-вывода.",e);
        }
        Demo01Logging.demonstarteAllLevels(LOGGER);
    }
}

class MyHtmlFormatter extends Formatter {	
    //Возвращаем заголовочную часть HTML файла.	 
    @Override
    public String getHead(Handler h) {        
        return "<html><head><title>AppLog</title>" +
                "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">" +
                "</head><body>" + "<table border=1>" +
                "<tr bgcolor=CYAN><td>date</td><td>level</td><td>class</td><td>method</td>" +
                "<td>message</td><td>thrown message</td><td>stacktrace</td></tr>";
    }
    
    //Возвращаем хвост (конец) HTML файла.        
    @Override
    public String getTail(Handler h) {
        return "</table></body></html>";
    }
    
    //Форматируем одно сообщение в строку таблицы.	 
    @Override
    public String format(LogRecord record) {
        StringBuilder result=new StringBuilder();
        Date d = new Date();
        Level level = record.getLevel();
        /**
         * Ошибки будут выделены красным цветом, 
         * предупреждения - серым,
         * информационные сообщения - белым.  */
        if (level==Level.SEVERE) 
            result.append("<tr bgColor=Tomato><td>");
        else if (level==Level.WARNING)
            result.append("<tr bgColor=GRAY><td>"); 
        else 
            result.append("<tr bgColor=WHITE><td>");	
        
        result.append("\n").append(d).append("</td><td>")
                .append(record.getLevel().toString()).append("</td><td>")
                .append(record.getSourceClassName()).append("</td><td>")
                .append(record.getSourceMethodName()).append("</td><td>")
                .append(record.getMessage()).append("</td><td>");
        Throwable thrown = record.getThrown();
        if (thrown != null) { // Если было передано исключение, то выводим полный стек вызовов.
            result.append(record.getThrown().getMessage()).append("</td><td>");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            record.getThrown().printStackTrace(pw);
            String stackTrace = sw.toString();
            result.append(stackTrace).append("</td>");
        } else { // Просто пустые ячейки.
            result.append("</td><td>null").append("</td>");
        }
        // Конец строки
        result.append("</tr>\n");
        return result.toString();
    }
}