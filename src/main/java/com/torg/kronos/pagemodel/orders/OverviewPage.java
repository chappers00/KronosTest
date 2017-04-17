package com.torg.kronos.pagemodel.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * A Class following the page object model to represent the functions
 * available on the /orders/overview endpoint
 * @author Tom
 * Created 17/04/2017
 */
public class OverviewPage {
    private WebDriver driver;

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Gets the name of the customer from the top left summary section
     * @return A string of the customer's full name
     */
    public String getCustomerName() {
        WebElement customer = driver.findElement(By.cssSelector("a[title='View Full Profile']"));
        return customer.getText();
    }

    /**
     * Gets the email address the customer from the top left summary section
     * @return The customer's email address as a string
     */
    public String getCustomerEmail() {
        WebElement email = driver.findElement(By.cssSelector("a[title='Send Email']"));
        return email.getText();
    }

    /**
     * Gets the Phone Number of the customer from the top left summary section
     * @return The customer's phone number as a string
     */
    public String getCustomerPhoneNumber() {
        WebElement phone = driver.findElement(By.cssSelector("#last-contact-row > td:nth-child(2)"));
        return phone.getText();
    }

    /**
     * Returns a value from the order summary section on the top right hand side of the page
     * @param fieldName - The field to return, don't include the colon (:)
     * @return - A string representation of the value for the field
     * @throws org.openqa.selenium.NoSuchElementException if the field cannot be found
     */
    public String getOrderSummaryField(String fieldName) {
        WebElement element = driver.findElement(By.xpath("//div[@id='order-summary']/table/tbody/tr/td[contains(text(),'"+fieldName+"')]/following-sibling::td[1]"));
        return element.getText();
    }
}
