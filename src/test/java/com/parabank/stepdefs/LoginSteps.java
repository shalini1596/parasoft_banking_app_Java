package com.parabank.stepdefs;

import com.parabank.context.TestContext;
import com.parabank.pages.AccountsOverviewPage;
import com.parabank.pages.LoginPage;
import com.parabank.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
    private final LoginPage loginPage;
    private final AccountsOverviewPage overviewPage;

    public LoginSteps(TestContext context) {
        loginPage = new LoginPage(context.getDriver());
        overviewPage = new AccountsOverviewPage(context.getDriver());
    }

    @Given("the ParaBank login page is open")
    public void theLoginPageIsOpen() {
        loginPage.open();
    }

    @When("I log in with the configured customer credentials")
    public void loginWithConfiguredCredentials() {
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    @When("I log in with an invalid password")
    public void loginWithInvalidPassword() {
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("invalid.password"));
    }

    @Then("the accounts overview is displayed")
    public void accountsOverviewIsDisplayed() {
        String pageText = overviewPage.getOverviewText();
        Assert.assertTrue(pageText.contains("Accounts Overview"),
                "Expected the account overview after a successful login; current page text was: "
                        + pageText);
    }

    @Then("a login error is displayed")
    public void loginErrorIsDisplayed() {
        Assert.assertFalse(loginPage.getErrorMessage().isBlank(), "Expected a login error message.");
    }
}
