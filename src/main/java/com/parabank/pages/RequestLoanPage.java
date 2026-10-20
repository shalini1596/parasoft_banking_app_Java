package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestLoanPage extends BasePage {
    private static final By LOAN_AMOUNT = By.id("amount");
    private static final By DOWN_PAYMENT = By.id("downPayment");
    private static final By FROM_ACCOUNT = By.id("fromAccountId");
    private static final By APPLY_BUTTON = By.cssSelector("#requestLoanForm input.button");

    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/requestloan.htm");
    }

    public void requestLoan(String amount, String downPayment, String account) {
        type(LOAN_AMOUNT, amount);
        type(DOWN_PAYMENT, downPayment);
        selectByValue(FROM_ACCOUNT, account);
        click(APPLY_BUTTON);
    }

    public String getResultText() {
        return text(By.id("rightPanel"));
    }
}
