package net.kiranatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Subsidiary class OzoHelper (central class in project ExamplesWithoutPlugInLibs)
 * project: MVN_Ex_Logging
 * @author Kiranatos
 */
public class OzoHelper {
    
    /**
     * 
     * @param decoration ставить null, обрамление пока не реализовал
     * @param lines рядки
     */
    public static void printMe(String decoration, String... lines){
        System.out.println("");
        if (decoration==null){
            for (String line : lines) {
                System.out.println(line);                
            }
        }
    }
    
    /**
     * Усыпить поток на @milliseconds
     */
    public static void sleepMilliseconds(long milliseconds){
        try {
            Thread.sleep(milliseconds);
            //TimeUnit.SECONDS.sleep(milliseconds);
        } catch (InterruptedException ex) { System.out.println("\n\n Errorr !!! \n\n"); }
    }
    /**
     * Усыпить поток на @seconds
     */
    public static void sleepSeconds(long seconds){
        try {
            Thread.sleep(seconds * 1000);
            //TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ex) { System.err.println("\nInterruptedException in class OzoHelper, in method sleepSeconds"); }
    }
    /**
     * Усыпить поток на @minute
     */
    public static void sleepMinute(int minute){
        try {
            Thread.sleep(minute*60*1000);
            //TimeUnit.SECONDS.sleep(minute);
        } catch (InterruptedException ex) { System.out.println("\n\n Errorr !!! \n\n"); }
    }
    
       /**
     * Чтение рядка с панели
     */
    public static String getRead() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String theChoice = "empty string";
        
        try {
            theChoice = reader.readLine();
        } catch (IOException ex) {
            System.out.println("__ Reading Error!!! __");
            Logger.getLogger(OzoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return theChoice;
    }
    
    public static void fileExist(String path) {
        File file = new File(path);
        System.out.println("File exist? " + file.exists());        
    }
}