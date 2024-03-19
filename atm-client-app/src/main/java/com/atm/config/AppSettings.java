package com.atm.config;

public final class AppSettings {

    public static final String SERVER_ADDRESS = "http://localhost:";
    public static final int SERVER_PORT = 8080;
    public static final String PATH = "/";

    public static String URL() {
        return SERVER_ADDRESS + SERVER_PORT + PATH;
    }

}
