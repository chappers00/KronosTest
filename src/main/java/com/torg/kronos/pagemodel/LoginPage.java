package com.torg.kronos.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * A Class following the page object model to represent the functions
 * available on the Login endpoint
 * @author Tom
 * Created 15/04/2017
 */
public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Login to Kronos using the details provided
     * @param username - Username to login with
     * @param pw - Password to login with
     */
    public void login(String username, String pw) {
        WebElement usernameInput = driver.findElement(By.cssSelector("input#username"));
        usernameInput.sendKeys(username);
        WebElement pwInput = driver.findElement(By.cssSelector("input#password"));
        pwInput.sendKeys(pw);
        driver.findElement(By.cssSelector("input#submit-login")).click();
    }
}
