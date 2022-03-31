package com.customertimes.config;

import java.io.FileOutputStream;
import java.util.Properties;

public enum ReporterProperty {

    VERSION,
    ENVIRONMENT;

    private String value;

    public String get() {
        return value;
    }

    public void set(String value) {
        this.value = value;
    }

    public static void createAllureEnvironmentPropertiesFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            for (ReporterProperty property : ReporterProperty.values()) {
                if (property.value != null) {
                    props.setProperty(property.name(), property.value);
                }
            }
            props.store(fos, null);
            fos.close();

        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
