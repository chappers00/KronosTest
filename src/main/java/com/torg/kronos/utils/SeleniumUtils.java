package com.torg.kronos.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


/**
 * Set of helper methods to wrap common functions when working with WebDriver
 */
public class SeleniumUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumUtils.class);

    /**
     * Method that will see if an element is displayed every half a second for 5 seconds
     * If not will throw a NoSuchElementException
     * @param driver {@link WebDriver} the current WebDriver to use
     * @param selector {@link By} a CSS or XPATH selector for the WebElement
     */
    public static void waitForElementVisible(WebDriver driver, By selector) {
        for(int i=0; i<10; i++) {
            try {
                WebElement element = driver.findElement(selector);
                if(element.isDisplayed()) {
                    return;
                }
            } catch (NoSuchElementException e) {
                LOGGER.info("NoSuchElementException thrown on attempt #{} of 5");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException caught during driver sleep. Cleaning up", e);
                return;
            }
        }
        throw new NoSuchElementException(String.format("Gave up waiting for %s", selector));
    }

    public static void fillOutLoginPrompt(String user, String pw) throws InterruptedException{
        //Handle the initial basic auth popup
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            LOGGER.error("Whoops", e);
        }
        sendText(robot, user);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(1000);
        sendText(robot, pw);
        robot.keyPress(KeyEvent.VK_ENTER);

    }

    private static void sendText(Robot robot, String textToSend) {
        //Stick the username in the clipboard
        StringSelection stringSelection = new StringSelection(textToSend);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }
}
