package com.torg.kronos.steps;

import com.torg.kronos.pagemodel.LoginPage;
import com.torg.kronos.pagemodel.SiteMapPage;
import com.torg.kronos.pagemodel.orders.OverviewPage;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Step defintions for the Kronos application
 * @author Tom
 * Created 17/04/2017
 */
public class KronosStepDefinitions implements En {
    private WebDriver driver;
    private Properties prop;
    private static final Logger LOGGER = LoggerFactory.getLogger(KronosStepDefinitions.class);

    @Before
    public void setUpDriver() throws IOException {
        prop = new Properties();
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("acceptInsecureCerts", true);
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

    public KronosStepDefinitions() {
        Given("^(.*) has logged into (.*)$", (String user, String url) -> {
            String basicAuthUser = prop.getProperty("basicAuth.username");
            String basicAuthPw = prop.getProperty("basicAuth.password");
            String authURL = null;
            try {
                authURL = "https://"+basicAuthUser+":"+ URLEncoder.encode(basicAuthPw, "UTF-8")+"@"+url;
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Unable to encode password, terminating", e);
                assertFalse(true);
            }
            driver.get(authURL);
            LoginPage lp = new LoginPage(driver);
            lp.login(user, prop.getProperty(user+".password"));

        });

        When("^I search for an '(.*)' of '(.*)'$", (String searchType, String searchTerm) -> {
            SiteMapPage sm = new SiteMapPage(driver);
            sm.setSearchType(searchType);
            sm.enterSearchTermAndSearch(searchTerm);
        });

        Then("^the customer's details should be:$", (DataTable details) -> {
            OverviewPage op = new OverviewPage(driver);
            for (CustomerDetails detail : details.asList(CustomerDetails.class)) {
                assertEquals("Customer name doesn't match", detail.getName(), op.getCustomerName());
                assertEquals("Customer email doesn't match", detail.getEmail(), op.getCustomerEmail());
                assertEquals("Customer phone number doesn't match", detail.getTelephoneNumber(), op.getCustomerPhoneNumber());
            }
        });

        Then("^the following fields should be set in the order summary:$", (DataTable fields) -> {
            OverviewPage op = new OverviewPage(driver);
            Map<String, String> fieldMap = fields.asMap(String.class, String.class);
            fieldMap.forEach((k,v) -> assertEquals("Field: "+k+" doesn't match", v, op.getOrderSummaryField(k)));
        });
    }
}
