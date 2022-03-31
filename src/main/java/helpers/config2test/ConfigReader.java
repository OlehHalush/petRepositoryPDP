package com.customertimes.config;

import com.customertimes.util.ThreadUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final String RUN_PROPERTIES_PATH = "run.properties";
    private static Properties runProperties;

    public static String getParallelRunParameter(String parameterKey, String defaultParameterKey) {
        try {
            return ConfigReader.getParallelRunParameter(parameterKey);
        } catch (IllegalStateException ignored) {
        }
        try {
            return ConfigReader.getParallelRunParameter(defaultParameterKey);
        } catch (IllegalStateException ignored) {
        }
        throw new IllegalStateException("Parameter key '" + parameterKey
                + "' is not defined as well as it's default key '" + defaultParameterKey
                + "'. Please set it as run parameter or in run.properties file");
    }

    public static String getParallelRunParameter(String parameterKey) {
        return ConfigReader.getRunParameter(ThreadUtils.getRunParameter(parameterKey));
    }

    public static String getRunParameter(String parameterKey, String defaultParameterKey) {
        try {
            return ConfigReader.getRunParameter(parameterKey);
        } catch (IllegalStateException ignored) {
        }
        try {
            return ConfigReader.getRunParameter(defaultParameterKey);
        } catch (IllegalStateException ignored) {
        }
        throw new IllegalStateException("Parameter key '" + parameterKey
                + "' is not defined as well as it's default key '" + defaultParameterKey
                + "'. Please set it as run parameter or in run.properties file");
    }

    public static String getRunParameter(String parameterKey) {
        String parameterValue = System.getProperty(parameterKey);
        if (parameterValue != null) {
            return parameterValue;
        }
        try {
            parameterValue = getRunProperties().getProperty(parameterKey);
            if (parameterValue != null) {
                return parameterValue;
            }
        } catch (IllegalStateException ignored) {
        }
        throw new IllegalStateException("Parameter '" + parameterKey
                + "' is not defined. Please set it as run parameter or in run.properties file");
    }

    private static Properties getRunProperties() {
        if (runProperties == null) {
            try (InputStream input = new FileInputStream(RUN_PROPERTIES_PATH)) {
                runProperties = new Properties();
                runProperties.load(input);
            } catch (IOException ex) {
                throw new IllegalStateException("Trying to read from unexistent '" + RUN_PROPERTIES_PATH + "' file. " +
                        "Please be sure that you have all needed arguments set as run parameters or you have run.properties " +
                        "with appropriate arguments");
            }
        }
        return runProperties;
    }
}
