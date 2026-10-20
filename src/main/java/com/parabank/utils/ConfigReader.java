package com.parabank.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPERTIES = loadProperties();

    private ConfigReader() {
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ConfigReader.class.getResourceAsStream("/config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Missing /config.properties on the test classpath.");
            }
            properties.load(input);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load /config.properties.", exception);
        }
    }

    public static String get(String key) {
        String value = System.getProperty(key);
        if (value == null || value.isBlank()) {
            value = System.getenv(toEnvironmentVariable(key));
        }
        if (value == null || value.isBlank()) {
            value = PROPERTIES.getProperty(key);
        }
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Required configuration property '" + key + "' is missing.");
        }
        value = value.trim();
        if (value.startsWith("${") && value.endsWith("}")) {
            String variable = value.substring(2, value.length() - 1);
            String replacement = System.getProperty(variable);
            if (replacement == null || replacement.isBlank()) {
                replacement = System.getenv(variable);
            }
            if (replacement == null || replacement.isBlank()) {
                throw new IllegalStateException("Set environment variable '" + variable
                        + "' or override configuration property '" + key + "'.");
            }
            value = replacement;
        }
        return value;
    }

    private static String toEnvironmentVariable(String key) {
        return key.replaceAll("([a-z])([A-Z])", "$1_$2")
                .replace('.', '_')
                .replace('-', '_')
                .toUpperCase();
    }
}
