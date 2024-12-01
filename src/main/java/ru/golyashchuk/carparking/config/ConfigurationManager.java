package ru.golyashchuk.carparking.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        String result;
        try {
            result = resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return null;
        }
        return result;
    }
}
