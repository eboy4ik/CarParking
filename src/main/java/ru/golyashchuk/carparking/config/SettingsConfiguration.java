package ru.golyashchuk.carparking.config;

public class SettingsConfiguration {
    private SettingsConfiguration() {
    }

    public static int getWindowWidth() {
        String s = ConfigurationManager.getProperty("screen.width");
        int result;
        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 1024;
        }
        return Math.max(1024, result);
    }

    public static int getWindowHeight() {
        String s = ConfigurationManager.getProperty("screen.height");
        int result;
        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 720;
        }
        return Math.max(720, result);
    }
}
