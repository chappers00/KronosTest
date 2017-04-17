package com.torg.kronos.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * A Class following the page object model to represent the functions
 * available on the /help/sitemap endpoint which also forms the homepage of Kronos
 * @author Tom
 * Created 17/04/2017
 */
public class SiteMapPage {
    private WebDriver driver;

    public SiteMapPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Using the search feature in the top right of the SiteMapPage type in a search term
     * and press the ENTER key to submit the search. Use setSearchType function to select a search type first
     * @param searchTerm - A string to search for
     */
    public void enterSearchTermAndSearch(String searchTerm) {
        WebElement searchBox = driver.findElement(By.cssSelector("input#hsq"));
        searchBox.sendKeys(searchTerm);
        searchBox.sendKeys(Keys.ENTER);
    }

    /**
     * Using the drop down on the top right of the site map page select a type of object
     * to search for based on the type String.
     * @param type - The type of element to search for (I.E Orders, Customers, Academics)
     */
    public void setSearchType(String type) {
        Select typeDropDown = new Select(driver.findElement(By.cssSelector("select[name='hst']")));
        typeDropDown.selectByVisibleText(type);
    }
}
