package com.parabank.pages.components;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountServicesMenu extends BasePage {
    public AccountServicesMenu(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String service) {
        click(By.linkText(service));
    }

    public void logout() {
        click(By.linkText("Log Out"));
    }

    public boolean isLoggedIn() {
        return isVisible(By.linkText("Accounts Overview"));
    }
}
