package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindTransactionsPage extends BasePage {
    private static final By ACCOUNT = By.id("accountId");
    private static final By FIND_BY_AMOUNT = By.id("findByAmount");
    private static final By AMOUNT = By.id("amount");
    private static final By RESULTS = By.id("transactionTable");

    public FindTransactionsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/findtrans.htm");
    }

    public void findByAmount(String account, String amount) {
        selectByValue(ACCOUNT, account);
        type(AMOUNT, amount);
        click(FIND_BY_AMOUNT);
    }

    public String getResultsText() {
        return text(By.id("rightPanel"));
    }

    public boolean hasTransactionResults() {
        return isVisible(RESULTS);
    }
}
