package com.parabank.utils;

import com.parabank.models.User;

import java.time.Instant;

public final class DataGenerator {
    private DataGenerator() {
    }

    public static User newRegistrationUser() {
        String suffix = Long.toString(Instant.now().toEpochMilli());
        return new User(
                ConfigReader.get("registration.first.name"),
                ConfigReader.get("registration.last.name"),
                ConfigReader.get("registration.address"),
                ConfigReader.get("registration.city"),
                ConfigReader.get("registration.state"),
                ConfigReader.get("registration.zip.code"),
                ConfigReader.get("registration.phone"),
                ConfigReader.get("registration.ssn"),
                ConfigReader.get("registration.username.prefix") + suffix,
                ConfigReader.get("registration.password"));
    }
}
