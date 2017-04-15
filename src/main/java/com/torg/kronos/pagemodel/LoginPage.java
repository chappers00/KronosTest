package com.torg.kronos.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Tom on 15/04/2017.
 */
public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String pw) {
        WebElement usernameInput = driver.findElement(By.cssSelector("input#username"));
        usernameInput.sendKeys(username);
        WebElement pwInput = driver.findElement(By.cssSelector("input#password"));
        pwInput.sendKeys(pw);
        driver.findElement(By.cssSelector("input#submit-login")).click();
    }
}
