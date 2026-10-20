package com.parabank.pages;

import com.parabank.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By USERNAME = By.name("username");
    private static final By PASSWORD = By.name("password");
    private static final By LOGIN_BUTTON = By.cssSelector("input.button[value='Log In']");
    private static final By ERROR_MESSAGE = By.cssSelector("#rightPanel .error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open("/index.htm");
    }

    public void login(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        click(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return text(ERROR_MESSAGE);
    }

    public boolean isLoginPageDisplayed() {
        return isVisible(By.cssSelector("#loginPanel"));
    }
}
