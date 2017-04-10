package com.andersenlab;

import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class for configure properties for log4j
 *
 * @author Vlad Badilovskii
 * @version 1.0
 */
public class Config {
    private static Properties logProperties = new Properties();
    private String logFile;

    public Config(String logFile) {
        this.logFile = logFile;
    }

    /**
     * Method for reading property file
     */
    public void init() {
        try {
            FileInputStream fileInputStream = new FileInputStream(logFile);
            logProperties.load(fileInputStream);
            PropertyConfigurator.configure(logProperties);
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
