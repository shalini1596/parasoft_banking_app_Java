package com.parabank.stepdefs;

import com.parabank.context.TestContext;
import com.parabank.pages.RegisterPage;
import com.parabank.utils.DataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class RegisterSteps {
    private final TestContext context;
    private final RegisterPage registerPage;

    public RegisterSteps(TestContext context) {
        this.context = context;
        registerPage = new RegisterPage(context.getDriver());
    }

    @Given("the registration page is open")
    public void theRegistrationPageIsOpen() {
        registerPage.open();
    }

    @When("I register a new customer")
    public void registerNewCustomer() {
        context.setRegistrationUser(DataGenerator.newRegistrationUser());
        registerPage.register(context.getRegistrationUser());
    }

    @Then("the new customer registration succeeds")
    public void registrationSucceeds() {
        Assert.assertTrue(registerPage.getResultText().contains("Your account was created successfully"),
                "Expected a successful registration confirmation.");
    }
}
