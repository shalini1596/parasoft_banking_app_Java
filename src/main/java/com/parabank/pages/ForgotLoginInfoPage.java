package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotLoginInfoPage extends BasePage {
    public ForgotLoginInfoPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/lookup.htm");
    }

    public void lookup(String firstName, String lastName, String address, String city,
                       String state, String zipCode, String ssn) {
        type(By.id("firstName"), firstName);
        type(By.id("lastName"), lastName);
        type(By.id("address.street"), address);
        type(By.id("address.city"), city);
        type(By.id("address.state"), state);
        type(By.id("address.zipCode"), zipCode);
        type(By.id("ssn"), ssn);
        click(By.cssSelector("#lookupForm input.button"));
    }

    public String getResultText() {
        return text(By.id("rightPanel"));
    }
}
