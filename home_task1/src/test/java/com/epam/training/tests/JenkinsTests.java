package com.epam.training.tests;

import com.epam.training.pages.HomePage;
import com.epam.training.pages.ManagePage;
import com.epam.training.pages.StartPage;
import com.epam.training.pages.UserPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class JenkinsTests {

    private static WebDriver driver;
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROMEDRIVER_EXE_PATH = "";
    private StringBuffer verificationError = new StringBuffer();

    @BeforeClass
    public void beforeClass(){
        System.setProperty(WEBDRIVER_CHROME_DRIVER, CHROMEDRIVER_EXE_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(13, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

        StartPage startPage = new StartPage(driver);
        startPage.open();
        //авторизация
        startPage.logIn();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
        String verification = verificationError.toString();
        if (!verification.equals("")){
            Assert.fail(verification);
        }
    }

    @Test
    public void sampleTest(){

        HomePage homePage = new HomePage(driver);

        //клик по ссылке «Manage Jenkins»
        ManagePage managePage = homePage.manage();

        //-> появляется элемент dt и элемент dd
        Assert.assertTrue(managePage.areDTandDDPresent());

        //клик по ссылке, внутри которой содержится элемент dt с текстом «Manage Users»
        UserPage userPage = managePage.manageUsers();

        //-> становится доступна ссылка «Create User»
        try{
            Assert.assertTrue(userPage.createUserAvailable());
        }catch (Error e){
            verificationError.append("'Create User' link is not available\n");
        }

        //клик по ссылке «Create User»
        userPage.createUser();

        //-> появляется форма с тремя полями типа text и двумя полями типа password, причём все поля должны быть пустыми
        try{
            Assert.assertTrue(userPage.hasFormWithFiveInputs());
        }catch (Error e){
            verificationError.append("No form with five inputs found\n");
        }

        //после заполнения полей формы и клика по кнопке с надписью «Create User» на странице
        // появляется строка таблицы (элемент tr), в которой есть ячейка (элемент td) с текстом «someuser»
        Assert.assertTrue(userPage.submitUser());

        //после клика по ссылке с атрибутом href равным «user/someuser/delete» появляется текст
        // «Are you sure about deleting the user from Jenkins?».
        Assert.assertTrue(userPage.deleteUser());

        //после клика по кнопке с надписью «Yes» на странице отсутствует строка таблицы (элемент tr), с ячейкой (элемент td)
        //с текстом «someuser». На странице отсутствует ссылка с атрибутом href равным «user/someuser/delete».
        //На странице отсутствует ссылка с атрибутом href равным «user/admin/delete».
        Assert.assertTrue(userPage.doDeleteUser());

    }

}
