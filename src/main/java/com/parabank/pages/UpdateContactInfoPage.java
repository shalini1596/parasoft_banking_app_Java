package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpdateContactInfoPage extends BasePage {
    private static final By UPDATE_BUTTON = By.cssSelector("#customerForm input.button");

    public UpdateContactInfoPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/updateprofile.htm");
    }

    public void update(String firstName, String lastName, String address, String city,
                       String state, String zipCode, String phone) {
        type(By.id("customer.firstName"), firstName);
        type(By.id("customer.lastName"), lastName);
        type(By.id("customer.address.street"), address);
        type(By.id("customer.address.city"), city);
        type(By.id("customer.address.state"), state);
        type(By.id("customer.address.zipCode"), zipCode);
        type(By.id("customer.phoneNumber"), phone);
        click(UPDATE_BUTTON);
    }

    public String getResultText() {
        return text(By.id("rightPanel"));
    }
}
