package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenNewAccountPage extends BasePage {
    private static final By ACCOUNT_TYPE = By.id("type");
    private static final By SOURCE_ACCOUNT = By.id("fromAccountId");
    private static final By OPEN_BUTTON = By.cssSelector("#openAccountForm input.button");
    private static final By NEW_ACCOUNT_NUMBER = By.id("newAccountId");

    public OpenNewAccountPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/openaccount.htm");
    }

    public void openNewAccount(String type, String sourceAccount) {
        selectByVisibleText(ACCOUNT_TYPE, type);
        selectByValue(SOURCE_ACCOUNT, sourceAccount);
        click(OPEN_BUTTON);
    }

    public String getNewAccountNumber() {
        return text(NEW_ACCOUNT_NUMBER);
    }
}
