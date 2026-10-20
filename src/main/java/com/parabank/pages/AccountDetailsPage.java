package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDetailsPage extends BasePage {
    public AccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getDetailsText() {
        return text(By.id("rightPanel"));
    }

    public boolean hasTransactionTable() {
        return isVisible(By.id("transactionTable"));
    }
}
