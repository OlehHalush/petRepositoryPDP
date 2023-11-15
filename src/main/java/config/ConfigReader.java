package config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    private static final String RUN_PROPERTIES_PATH = "run.properties";

    private static Properties runProperties;

    public static String getRunParameter(String parameterKey) {
        String parameterValue = System.getProperty(parameterKey);
        if (parameterValue != null) {
            return parameterValue;
        }
        parameterValue = getRunProperties().getProperty(parameterKey);
        if (parameterValue != null) {
            return parameterValue;
        }
        throw new IllegalStateException("Parameter '" + parameterKey
                + "' is not defined. Please set it as run parameter or in run.properties file");
    }

    private static Properties getRunProperties() {
        if (runProperties == null) {
            try (InputStream input = Files.newInputStream(Paths.get(RUN_PROPERTIES_PATH))) {
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
