package com.epam.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class UserPage extends Page{
    public UserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static final String URL = "http://localhost:8080/jenkins/securityRealm/";

    @FindBy(xpath = "//a[text()[contains(., 'Create User')]]")
    private WebElement create_user_link;

    @FindBy(xpath = "//div[@id='main-panel']/form")
    private WebElement user_data_form;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='password1']")
    private WebElement password1;

    @FindBy(xpath = "//input[@name='password2']")
    private WebElement password2;

    @FindBy(xpath = "//input[@name='fullname']")
    private WebElement fullname;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//form//button[@id='yui-gen1-button']")
    private WebElement create_user_button;

    @FindBy(xpath = "//tr//td[text()[contains(., 'someuser')]]")
    private WebElement trtd;

    @FindBy(xpath = "//*[text()[contains(., 'Are you sure about deleting the user from Jenkins?')]]")
    private WebElement text_contains_element;

    @FindBy(xpath = "//a[@href='user/someuser/delete']")
    private WebElement delete_user_link;

    @FindBy(xpath = "//button[text()[contains(., 'Yes')]]")
    private WebElement do_delete_button;

    @FindBy(xpath = "//a[@href='user/admin/delete']")
    private WebElement delete_admin_link;

    public void open() {
        driver.get(URL);
    }

    public boolean createUserAvailable(){

        if (create_user_link!=null){
            return true;
        }
        return false;

    }

    public UserPage createUser(){
        create_user_link.click();
        return this;
    }

    public boolean hasFormWithFiveInputs(){

        List<WebElement> inputs = user_data_form.findElements(By.xpath("//input"));
        int text_input = 0, password_input = 0;

        for (WebElement input : inputs){
            if (input.getAttribute("type").equals("text")){
                text_input++;
            }else if (input.getAttribute("type").equals("password")){
                password_input++;
            }
        }

        if ((text_input==3) && (password_input==2) && inputs.size()==5){
            return true;
        }

        return false;
    }

    public boolean submitUser(){

        username.sendKeys("someuser");
        password1.sendKeys("somepass");
        password2.sendKeys("somepass");
        fullname.sendKeys("somefulluser");
        email.sendKeys("someaddr@addr.com");

        create_user_button.click();

        if (trtd!=null){
            return true;
        }

        return false;
    }

    public boolean deleteUser(){

        delete_user_link.click();

        if (text_contains_element!=null){
            return true;
        }

        return false;
    }

    public boolean doDeleteUser(){

        do_delete_button.click();

        if (!isElementPresent(trtd) && !isElementPresent(delete_user_link) && !isElementPresent(delete_admin_link)){
            return true;
        }
        return false;
    }

    public WebElement getCreate_user_link() {
        return create_user_link;
    }

    public void setCreate_user_link(WebElement create_user_link) {
        this.create_user_link = create_user_link;
    }

    public WebElement getUser_data_form() {
        return user_data_form;
    }

    public void setUser_data_form(WebElement user_data_form) {
        this.user_data_form = user_data_form;
    }

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(WebElement username) {
        this.username = username;
    }

    public WebElement getPassword1() {
        return password1;
    }

    public void setPassword1(WebElement password1) {
        this.password1 = password1;
    }

    public WebElement getPassword2() {
        return password2;
    }

    public void setPassword2(WebElement password2) {
        this.password2 = password2;
    }

    public WebElement getFullname() {
        return fullname;
    }

    public void setFullname(WebElement fullname) {
        this.fullname = fullname;
    }

    public WebElement getEmail() {
        return email;
    }

    public void setEmail(WebElement email) {
        this.email = email;
    }

    public WebElement getCreate_user_button() {
        return create_user_button;
    }

    public void setCreate_user_button(WebElement create_user_button) {
        this.create_user_button = create_user_button;
    }

    public WebElement getTrtd() {
        return trtd;
    }

    public void setTrtd(WebElement trtd) {
        this.trtd = trtd;
    }

    public WebElement getText_contains_element() {
        return text_contains_element;
    }

    public void setText_contains_element(WebElement text_contains_element) {
        this.text_contains_element = text_contains_element;
    }

    public WebElement getDelete_user_link() {
        return delete_user_link;
    }

    public void setDelete_user_link(WebElement delete_user_link) {
        this.delete_user_link = delete_user_link;
    }

    public WebElement getDo_delete() {
        return do_delete_button;
    }

    public void setDo_delete(WebElement do_delete) {
        this.do_delete_button = do_delete;
    }

    public WebElement getDelete_admin_link() {
        return delete_admin_link;
    }

    public void setDelete_admin_link(WebElement delete_admin_link) {
        this.delete_admin_link = delete_admin_link;
    }
}
