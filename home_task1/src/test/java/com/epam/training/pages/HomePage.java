package com.epam.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static final String URL = "http://localhost:8080/jenkins/";

    @FindBy(xpath = "//a[text()[contains(., 'Manage Jenkins')]]")
    private WebElement manage_Jenkins_link;

    public WebElement getLink() {
        return manage_Jenkins_link;
    }

    public void setLink(WebElement link) {
        manage_Jenkins_link = link;
    }

    public void open() {
        driver.get(URL);
    }

    public ManagePage manage() {

        manage_Jenkins_link.click();
        return new ManagePage(driver);
    }

}
