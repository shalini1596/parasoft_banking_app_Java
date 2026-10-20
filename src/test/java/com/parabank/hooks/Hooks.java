package com.parabank.hooks;

import com.parabank.base.DriverFactory;
import com.parabank.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void startBrowser() {
        WebDriver driver = DriverFactory.createDriver();
        driver.get(com.parabank.utils.ConfigReader.get("base.url"));
    }

    @After
    public void stopBrowser(Scenario scenario) {
        try {
            WebDriver driver = context.getDriver();
            if (scenario.isFailed() && driver instanceof TakesScreenshot screenshotDriver) {
                byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
                Allure.addAttachment(scenario.getName(), "image/png",
                        new ByteArrayInputStream(screenshot), ".png");
            }
        } finally {
            DriverFactory.quitDriver();
        }
    }
}
