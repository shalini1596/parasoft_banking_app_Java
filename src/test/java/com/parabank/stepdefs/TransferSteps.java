package com.parabank.stepdefs;

import com.parabank.context.TestContext;
import com.parabank.pages.AccountsOverviewPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.TransferFundsPage;
import com.parabank.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class TransferSteps {
    private final LoginPage loginPage;
    private final AccountsOverviewPage overviewPage;
    private final TransferFundsPage transferPage;

    public TransferSteps(TestContext context) {
        loginPage = new LoginPage(context.getDriver());
        overviewPage = new AccountsOverviewPage(context.getDriver());
        transferPage = new TransferFundsPage(context.getDriver());
    }

    @Given("I am logged in to ParaBank")
    public void loggedIn() {
        loginPage.open();
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
        Assert.assertTrue(overviewPage.getOverviewText().contains("Accounts Overview"),
                "Login failed; account operations cannot continue.");
    }

    @When("I transfer the configured amount between my first two accounts")
    public void transferConfiguredAmount() {
        List<String> accounts = overviewPage.getAccountNumbers();
        Assert.assertTrue(accounts.size() >= 2, "A transfer requires at least two accounts.");
        transferPage.open();
        transferPage.transfer(ConfigReader.get("transfer.amount"), accounts.get(0), accounts.get(1));
    }

    @Then("the transfer confirmation is displayed")
    public void transferConfirmationDisplayed() {
        Assert.assertTrue(transferPage.getResultText().contains("Transfer Complete"),
                "Expected a transfer confirmation.");
    }
}
