package com.parabank.stepdefs;

import com.parabank.context.TestContext;
import com.parabank.pages.ContactUsPage;
import com.parabank.pages.ForgotLoginInfoPage;
import com.parabank.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

public class PublicPageSteps {
    private final TestContext context;
    private final ContactUsPage contactUsPage;
    private final ForgotLoginInfoPage forgotLoginInfoPage;

    public PublicPageSteps(TestContext context) {
        this.context = context;
        contactUsPage = new ContactUsPage(context.getDriver());
        forgotLoginInfoPage = new ForgotLoginInfoPage(context.getDriver());
    }

    @Given("I open the ParaBank {string} page")
    public void openPublicPage(String page) {
        context.getDriver().get(ConfigReader.get("base.url"));
        context.getDriver().findElement(By.linkText(page)).click();
    }

    @Then("the page heading contains {string}")
    public void pageHeadingContains(String expected) {
        Assert.assertTrue(context.getDriver().findElement(By.id("rightPanel")).getText().contains(expected),
                "Expected page content to contain: " + expected);
    }

    @When("I submit the contact form with configured details")
    public void submitContactForm() {
        contactUsPage.submit(ConfigReader.get("contact.name"), ConfigReader.get("contact.email"),
                ConfigReader.get("contact.phone"), ConfigReader.get("contact.message"));
    }

    @Then("the contact form confirmation is displayed")
    public void contactFormConfirmationDisplayed() {
        Assert.assertTrue(contactUsPage.getResultText().contains("Thank you"),
                "Expected contact form confirmation.");
    }

    @When("I look up my configured login details")
    public void lookupLoginDetails() {
        forgotLoginInfoPage.lookup(ConfigReader.get("registration.first.name"),
                ConfigReader.get("registration.last.name"), ConfigReader.get("registration.address"),
                ConfigReader.get("registration.city"), ConfigReader.get("registration.state"),
                ConfigReader.get("registration.zip.code"), ConfigReader.get("registration.ssn"));
    }

    @Then("the login lookup result is displayed")
    public void loginLookupResultDisplayed() {
        Assert.assertFalse(forgotLoginInfoPage.getResultText().isBlank(),
                "Expected a login information lookup response.");
    }
}
