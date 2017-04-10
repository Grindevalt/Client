package com.andersenlab;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Client class for connecting to server database
 *
 * @author Vlad Badilovskii
 * @version 1.0
 */
public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getSimpleName());
    private static final String LOG_PROPERTIES_FILE = "C:\\Users\\Grindevalt\\Documents\\GitHub\\Client\\src\\main\\resources\\log4j.properties";
    private static final String END = "end";

    public static void main(String[] args) throws IOException {
        Config config = new Config(LOG_PROPERTIES_FILE);
        config.init();

        LOGGER.info("The program is starting");
        System.out.println("Welcome to Client side");

        Socket socketFromServer = new Socket("localhost", 4444);
        LOGGER.info("Socket socketFromServer is successfully created");

        BufferedReader dataFromServer = new BufferedReader(new InputStreamReader(new BufferedInputStream(socketFromServer.getInputStream())));
        LOGGER.info("BufferedReader dataFromServer is successfully created");

        BufferedReader dataFromConsole = new BufferedReader(new InputStreamReader(System.in));
        LOGGER.info("BufferedReader dataFromConsole is successfully created");

        PrintWriter dataToServer = new PrintWriter(socketFromServer.getOutputStream(), true);
        LOGGER.info("PrintWriter dataToServer is successfully created");

        try {
            LOGGER.info("Entering to the cycle");
            while (true) {
                serverAnswer(dataFromServer);
                LOGGER.info("Data from server are received");
                clientRequest(dataFromConsole, dataToServer);
                LOGGER.info("Request from client to server successfully sent");
            }
        } catch (SocketException e){
            LOGGER.warn("Connection with server is over");
            System.out.println("Bye-Bye");
        }
        finally {
            socketFromServer.close();
            LOGGER.info("Socket socketFromServer is successfully closed");
            dataFromServer.close();
            LOGGER.info("BufferedReader dataFromServer is successfully closed");
            dataFromConsole.close();
            LOGGER.info("BufferedReader dataFromConsole is successfully closed");
            dataToServer.close();
            LOGGER.info("PrintWriter dataToServer is successfully closed");
        }
    }

    private static void clientRequest(BufferedReader inu, PrintWriter out) throws IOException {
        String fUser;
        fUser = inu.readLine();
        out.println(fUser);
    }

    private static void serverAnswer(BufferedReader in) throws IOException {
        while (true) {
            String fServer;
            fServer = in.readLine();
            if (fServer.equals(END)) break;
            System.out.println(fServer);
        }
    }
}


