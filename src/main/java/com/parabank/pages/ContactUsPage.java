package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/contact.htm");
    }

    public void submit(String name, String email, String phone, String message) {
        type(By.id("name"), name);
        type(By.id("email"), email);
        type(By.id("phone"), phone);
        type(By.id("message"), message);
        click(By.cssSelector("#contactForm input.button"));
    }

    public String getResultText() {
        return text(By.id("rightPanel"));
    }
}
