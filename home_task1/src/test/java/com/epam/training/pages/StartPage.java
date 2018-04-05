package com.epam.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends Page{
    public StartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static final String URL = "http://localhost:8080/jenkins/";
    private static final String LOGIN = "";
    private static final String PASSWORD = "";

    @FindBy (xpath = "//input[@name='j_username']")
    private WebElement username;

    @FindBy (xpath = "//input[@name='j_password']")
    private WebElement password;

    @FindBy (xpath = "//form[@name='login']//button")
    private WebElement log_in_button;

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(WebElement username) {
        this.username = username;
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(WebElement password) {
        this.password = password;
    }

    public WebElement getButton() {
        return log_in_button;
    }

    public void setButton(WebElement button) {
        log_in_button = button;
    }

    public void open() {
        driver.get(URL);
    }

    public void logIn(){

        username.sendKeys(LOGIN);
        password.sendKeys(PASSWORD);
        log_in_button.click();

    }
}
