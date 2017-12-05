package sample;

import javafx.fxml.FXML;

import java.io.*;

public class Settings {

    private static final String SETTINGS_FILE = "C:/1/settings.set.txt";
    private static boolean useCache;
    private static String cachePath;
    private static boolean showLeadTime;

    public static boolean isUseCache() {
        return useCache;
    }

    public static void setUseCache(boolean useCache) {
        Settings.useCache = useCache;
    }

    public static String getCachePath() {
        return cachePath;
    }

    public static void setCachePath(String cachePath) {
        Settings.cachePath = cachePath;
    }

    public static boolean isShowLeadTime() {
        return showLeadTime;
    }

    public static void setShowLeadTime(boolean showLeadTime) {
        Settings.showLeadTime = showLeadTime;
    }

    public void initializeSettings() {
        readSettingsFromFile();
    }

    public static void saveSettings() {
        writeSettingsToFile();
    }

    private static void readSettingsFromFile() {


        try (BufferedReader br = new BufferedReader(new FileReader(new File(SETTINGS_FILE)))) {

            String sCurrentLine;

            sCurrentLine = br.readLine();

            if (!sCurrentLine.isEmpty()) {
                String[] settings = sCurrentLine.split(";");

                if (settings.length == 3) {
                    if (settings[0].compareToIgnoreCase("True") == 0) {
                        useCache = true;
                    } else if (settings[0].compareToIgnoreCase("False") == 0) {
                        useCache = false;
                    }
                    cachePath = settings[1].trim();

                    if (settings[2].compareToIgnoreCase("True") == 0) {
                        showLeadTime = true;
                    } else if (settings[2].compareToIgnoreCase("False") == 0) {
                        showLeadTime = false;
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void writeSettingsToFile() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(SETTINGS_FILE), "utf-8"))) {
            String settings = useCache + ";" + cachePath + ";" + showLeadTime;
            writer.write(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
