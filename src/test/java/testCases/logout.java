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
import pages.products;

public class logout {
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
    public void test_valid_user_logout() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.doLogin("standard_user","secret_sauce");
        softAssert.assertEquals(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).getText(), "ADD TO CART", "Valid User Login FAILED");
        products logoutUser = new products(driver);
        logoutUser.doLogout();
        softAssert.assertEquals(driver.findElement(By.id("login-button")).isDisplayed(), true);
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
