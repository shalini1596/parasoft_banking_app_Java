package com.parabank.pages;

import com.parabank.base.BasePage;
import com.parabank.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    private static final By REGISTER_BUTTON = By.cssSelector("#customerForm input[value='Register']");
    private static final By RESULT = By.id("rightPanel");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/register.htm");
    }

    public void register(User user) {
        type(By.id("customer.firstName"), user.getFirstName());
        type(By.id("customer.lastName"), user.getLastName());
        type(By.id("customer.address.street"), user.getAddress());
        type(By.id("customer.address.city"), user.getCity());
        type(By.id("customer.address.state"), user.getState());
        type(By.id("customer.address.zipCode"), user.getZipCode());
        type(By.id("customer.phoneNumber"), user.getPhoneNumber());
        type(By.id("customer.ssn"), user.getSsn());
        type(By.id("customer.username"), user.getUsername());
        type(By.id("customer.password"), user.getPassword());
        type(By.id("repeatedPassword"), user.getPassword());
        click(REGISTER_BUTTON);
    }

    public String getResultText() {
        return text(RESULT);
    }
}
