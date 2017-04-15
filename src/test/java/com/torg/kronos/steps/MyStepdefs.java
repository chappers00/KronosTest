package com.torg.kronos.steps;

import com.torg.kronos.pagemodel.LoginPage;
import com.torg.kronos.utils.SeleniumUtils;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static com.torg.kronos.utils.SeleniumUtils.fillOutLoginPrompt;
import static org.junit.Assert.assertTrue;

/**
 * Some Cucumber step definitions for the example project
 * Created by Tom on 14/04/2017.
 */
public class MyStepdefs implements En {
    private WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyStepdefs.class);
    Properties prop;



    @Before
    public void setUpDriver() throws IOException {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("acceptInsecureCerts", true);
        prop = new Properties();
        driver = new ChromeDriver(caps);
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
    }
    @After
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public MyStepdefs() {
        Given("^I've opened the google home page$", () -> driver.get("https://www.google.com"));

        When("^I search for '(.*)'$", (String searchTerm) -> {
            WebElement searchBox = driver.findElement(By.cssSelector("#lst-ib"));
            searchBox.sendKeys(searchTerm);
            searchBox.sendKeys(Keys.ENTER);
            //Wait for the search results to come back
            SeleniumUtils.waitForElementVisible(driver, By.cssSelector("#resultStats"));
        });
        Then("^there should be a result titled '(.*)'$", (String expectedResultTitle) -> {
            List<WebElement> results = driver.findElements(By.xpath("//h3/a"));
            LOGGER.info("Found {} search results", results.size());
            boolean matchingTitle = false;
            for(WebElement result : results) {
                String resultTitle = result.getText();
                LOGGER.info("Looking at {}", resultTitle);
                if (resultTitle.equalsIgnoreCase(expectedResultTitle)) {
                    LOGGER.info("{} matches {}", resultTitle, expectedResultTitle);
                    matchingTitle = true;
                    break;
                }
            }
            assertTrue("Didn't get a match for "+expectedResultTitle, matchingTitle);
        });
        Given("^(.*) has logged into (.*)$", (String user, String url) -> {
            String basicAuthUser = "torgstaging";
            String basicAuthPw = prop.getProperty(basicAuthUser);
            driver.get(url);
            try {
                Thread.sleep(1000);
                fillOutLoginPrompt(basicAuthUser, basicAuthPw);
            } catch (InterruptedException e) {
                LOGGER.error("Error", e);
            }
            LOGGER.info("Sent NTLM login details");
            LoginPage lp = new LoginPage(driver);
            lp.login(user, prop.getProperty(user));

        });
        When("^I search for an 'Orders' of 'OBE(\\d+)'$", (Integer arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^I can change the 'Order Origin' to a random entry from the list$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}
