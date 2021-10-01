package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.loginPage;

public class login {
    public WebDriver driver;
    public SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        softAssert = new SoftAssert();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void test_valid_user_login() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.doLogin("standard_user","secret_sauce");
        softAssert.assertEquals(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).getText(), "ADD TO CART", "Valid User Login FAILED");
        softAssert.assertAll();
    }

    @Test
    public void test_locked_user_login() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.doLogin("locked_out_user","secret_sauce");
        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]")).getText(), "Epic sadface: Sorry, this user has been locked out.", "Locked User Login FAILED");
        softAssert.assertAll();
    }

    @Test
    public void test_required_username() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.requiredFieldsUsername("secret_sauce");
        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]")).getText(), "Epic sadface: Username is required", "Required Username FAILED");
        softAssert.assertAll();
    }

    @Test
    public void test_required_password() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.requiredFieldsPassword("locked_out_user");
        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]")).getText(), "Epic sadface: Password is required", "Required Password FAILED");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
