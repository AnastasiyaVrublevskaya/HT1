package com.epam.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManagePage extends Page{
    public ManagePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static final String URL = "http://localhost:8080/jenkins/manage";

    @FindBy(xpath = "//dt[text()[contains(., 'Manage Users')]]")
    private WebElement dt;

    @FindBy(xpath = "//dd[text()[contains(., 'Create/delete/modify users that can log in to this Jenkins')]]")
    private WebElement dd;

    @FindBy(xpath = "//a[.//dt[text()[contains(., 'Manage Users')]]]")
    private WebElement manage_users_link;

    public WebElement getDt() {
        return dt;
    }

    public void setDt(WebElement dt) {
        this.dt = dt;
    }

    public WebElement getDd() {
        return dd;
    }

    public void setDd(WebElement dd) {
        this.dd = dd;
    }

    public WebElement getManage_users_link() {
        return manage_users_link;
    }

    public void setManage_users_link(WebElement manage_users_link) {
        this.manage_users_link = manage_users_link;
    }

    public void open() {
        driver.get(URL);
    }

    public boolean areDTandDDPresent(){

        if (dt!=null && dd!=null){
            return true;
        }
        return false;

    }

    public UserPage manageUsers() {

        manage_users_link.click();
        return new UserPage(driver);
    }
}
