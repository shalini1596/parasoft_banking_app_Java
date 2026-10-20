package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BillPayPage extends BasePage {
    private static final By PAY_BUTTON = By.cssSelector("#billpayForm input.button");
    private static final By RESULT = By.id("rightPanel");

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/billpay.htm");
    }

    public void pay(String name, String address, String city, String state, String zipCode,
                    String phone, String payeeAccount, String paymentAccount, String amount) {
        type(By.name("payee.name"), name);
        type(By.name("payee.address.street"), address);
        type(By.name("payee.address.city"), city);
        type(By.name("payee.address.state"), state);
        type(By.name("payee.address.zipCode"), zipCode);
        type(By.name("payee.phoneNumber"), phone);
        type(By.name("payee.accountNumber"), payeeAccount);
        type(By.name("verifyAccount"), payeeAccount);
        selectByValue(By.name("accountId"), paymentAccount);
        type(By.name("amount"), amount);
        click(PAY_BUTTON);
    }

    public String getResultText() {
        return text(RESULT);
    }
}
