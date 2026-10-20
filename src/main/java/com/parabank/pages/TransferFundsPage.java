package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferFundsPage extends BasePage {
    private static final By AMOUNT = By.id("amount");
    private static final By FROM_ACCOUNT = By.id("fromAccountId");
    private static final By TO_ACCOUNT = By.id("toAccountId");
    private static final By TRANSFER_BUTTON = By.cssSelector("#transferForm input.button");
    private static final By RESULT = By.id("rightPanel");

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/transfer.htm");
    }

    public void transfer(String amount, String fromAccount, String toAccount) {
        type(AMOUNT, amount);
        selectByValue(FROM_ACCOUNT, fromAccount);
        selectByValue(TO_ACCOUNT, toAccount);
        click(TRANSFER_BUTTON);
    }

    public String getResultText() {
        return text(RESULT);
    }
}
