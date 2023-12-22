package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static final String PATH_TO_PROPERTIES_FILE = "run.properties";

    public static Properties readProperties() {
        Properties properties = new Properties();
        FileInputStream input = null;

        try {
            input = new FileInputStream(PATH_TO_PROPERTIES_FILE);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return properties;
    }

    public static String getProperty(String property) {
        return readProperties().getProperty(property);
    }
}
