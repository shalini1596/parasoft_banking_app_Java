package com.parabank.context;

import com.parabank.base.DriverFactory;
import com.parabank.models.User;
import org.openqa.selenium.WebDriver;

public class TestContext {
    private User registrationUser;

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public User getRegistrationUser() {
        return registrationUser;
    }

    public void setRegistrationUser(User registrationUser) {
        this.registrationUser = registrationUser;
    }
}
