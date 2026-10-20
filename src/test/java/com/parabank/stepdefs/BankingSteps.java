package com.parabank.stepdefs;

import com.parabank.context.TestContext;
import com.parabank.pages.AccountsOverviewPage;
import com.parabank.pages.AccountDetailsPage;
import com.parabank.pages.components.AccountServicesMenu;
import com.parabank.pages.BillPayPage;
import com.parabank.pages.FindTransactionsPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.OpenNewAccountPage;
import com.parabank.pages.RequestLoanPage;
import com.parabank.pages.UpdateContactInfoPage;
import com.parabank.utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class BankingSteps {
    private final TestContext context;
    private final AccountsOverviewPage overviewPage;
    private final AccountDetailsPage accountDetailsPage;
    private final AccountServicesMenu servicesMenu;
    private final OpenNewAccountPage openAccountPage;
    private final BillPayPage billPayPage;
    private final FindTransactionsPage findTransactionsPage;
    private final UpdateContactInfoPage updateContactInfoPage;
    private final RequestLoanPage requestLoanPage;
    private String accountNumber;

    public BankingSteps(TestContext context) {
        this.context = context;
        overviewPage = new AccountsOverviewPage(context.getDriver());
        accountDetailsPage = new AccountDetailsPage(context.getDriver());
        servicesMenu = new AccountServicesMenu(context.getDriver());
        openAccountPage = new OpenNewAccountPage(context.getDriver());
        billPayPage = new BillPayPage(context.getDriver());
        findTransactionsPage = new FindTransactionsPage(context.getDriver());
        updateContactInfoPage = new UpdateContactInfoPage(context.getDriver());
        requestLoanPage = new RequestLoanPage(context.getDriver());
    }

    @When("I open a savings account using my first account")
    public void openSavingsAccount() {
        List<String> accounts = overviewPage.getAccountNumbers();
        Assert.assertFalse(accounts.isEmpty(), "An existing account is required.");
        openAccountPage.open();
        openAccountPage.openNewAccount("SAVINGS", accounts.get(0));
        accountNumber = openAccountPage.getNewAccountNumber();
    }

    @When("I open the details for my first account")
    public void openFirstAccountDetails() {
        List<String> accounts = overviewPage.getAccountNumbers();
        Assert.assertFalse(accounts.isEmpty(), "Account details require an account.");
        overviewPage.openAccount(accounts.get(0));
    }

    @Then("the account details and transaction history are displayed")
    public void accountDetailsAndTransactionsDisplayed() {
        Assert.assertTrue(accountDetailsPage.getDetailsText().contains("Account Details"),
                "Expected the account details page.");
        Assert.assertTrue(accountDetailsPage.hasTransactionTable(),
                "Expected the account transaction table.");
    }

    @When("I log out")
    public void logOut() {
        servicesMenu.logout();
    }

    @Then("the customer login form is displayed")
    public void customerLoginFormDisplayed() {
        Assert.assertTrue(new LoginPage(context.getDriver()).isLoginPageDisplayed(),
                "Expected the customer login form after logging out.");
    }

    @Then("the new account is listed in my overview")
    public void newAccountListed() {
        overviewPage.open();
        Assert.assertTrue(overviewPage.getAccountNumbers().contains(accountNumber),
                "Expected newly opened account " + accountNumber + " in the overview.");
    }

    @When("I pay the configured bill from my first account")
    public void payConfiguredBill() {
        List<String> accounts = overviewPage.getAccountNumbers();
        Assert.assertFalse(accounts.isEmpty(), "A bill payment requires an account.");
        billPayPage.open();
        billPayPage.pay(ConfigReader.get("payee.name"), ConfigReader.get("payee.address"),
                ConfigReader.get("payee.city"), ConfigReader.get("payee.state"),
                ConfigReader.get("payee.zip.code"), ConfigReader.get("payee.phone"),
                ConfigReader.get("payee.account.number"), accounts.get(0),
                ConfigReader.get("billpay.amount"));
    }

    @Then("the bill payment confirmation is displayed")
    public void billPaymentConfirmationDisplayed() {
        Assert.assertTrue(billPayPage.getResultText().contains("Bill Payment Complete"),
                "Expected a bill payment confirmation.");
    }

    @When("I find transactions by the configured amount")
    public void findTransactionsByAmount() {
        List<String> accounts = overviewPage.getAccountNumbers();
        Assert.assertFalse(accounts.isEmpty(), "Transaction search requires an account.");
        findTransactionsPage.open();
        findTransactionsPage.findByAmount(accounts.get(0), ConfigReader.get("transfer.amount"));
    }

    @Then("the transaction search results are displayed")
    public void transactionSearchResultsDisplayed() {
        Assert.assertTrue(findTransactionsPage.getResultsText().contains("Transaction Results"),
                "Expected transaction search results.");
    }

    @When("I update my contact details")
    public void updateContactDetails() {
        updateContactInfoPage.open();
        updateContactInfoPage.update(ConfigReader.get("profile.first.name"),
                ConfigReader.get("profile.last.name"), ConfigReader.get("profile.address"),
                ConfigReader.get("profile.city"), ConfigReader.get("profile.state"),
                ConfigReader.get("profile.zip.code"), ConfigReader.get("profile.phone"));
    }

    @Then("the contact details update confirmation is displayed")
    public void contactUpdateConfirmationDisplayed() {
        Assert.assertTrue(updateContactInfoPage.getResultText().contains("Profile Updated"),
                "Expected a profile update confirmation.");
    }

    @When("I submit the configured loan request")
    public void requestConfiguredLoan() {
        List<String> accounts = overviewPage.getAccountNumbers();
        Assert.assertFalse(accounts.isEmpty(), "A loan request requires an account.");
        requestLoanPage.open();
        requestLoanPage.requestLoan(ConfigReader.get("loan.amount"),
                ConfigReader.get("loan.down.payment"), accounts.get(0));
    }

    @Then("the loan decision is displayed")
    public void loanDecisionDisplayed() {
        String result = requestLoanPage.getResultText();
        Assert.assertTrue(result.contains("Loan Request Processed"),
                "Expected a loan decision.");
    }
}
