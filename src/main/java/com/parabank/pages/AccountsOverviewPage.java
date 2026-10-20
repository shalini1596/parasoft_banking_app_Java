package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class AccountsOverviewPage extends BasePage {
    private static final By ACCOUNT_LINKS = By.cssSelector("#accountTable a");

    public AccountsOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/overview.htm");
    }

    public List<String> getAccountNumbers() {
        return findAll(ACCOUNT_LINKS).stream().map(element -> element.getText().trim())
                .filter(value -> !value.isEmpty()).collect(Collectors.toList());
    }

    public void openAccount(String accountNumber) {
        click(By.linkText(accountNumber));
    }

    public String getOverviewText() {
        return text(By.id("rightPanel"));
    }
}
