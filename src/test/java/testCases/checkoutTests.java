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
import pages.checkout;

public class checkoutTests {
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
    public void test_required_fields() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.doLogin("standard_user","secret_sauce");
        softAssert.assertEquals(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).getText(), "ADD TO CART", "Valid User Login FAILED");
        products addBackpack = new products(driver);
        addBackpack.addToCart();
        addBackpack.goToCheckout();
        checkout form = new checkout(driver);
        form.validateRequired("Emanuel", "Medrano", "33195");
    }

    @Test
    public void test_cancel_checkout() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.doLogin("standard_user","secret_sauce");
        softAssert.assertEquals(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).getText(), "ADD TO CART", "Valid User Login FAILED");
        products addBackpack = new products(driver);
        addBackpack.addToCart();
        addBackpack.goToCheckout();
        checkout form = new checkout(driver);
        form.cancelCheckout();
    }

    @Test
    public void test_checkout_process() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.doLogin("standard_user","secret_sauce");
        softAssert.assertEquals(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).getText(), "ADD TO CART", "Valid User Login FAILED");
        products addBackpack = new products(driver);
        addBackpack.addToCart();
        addBackpack.goToCheckout();
        checkout form = new checkout(driver);
        form.checkoutProcess("Emanuel", "Medrano", "33195");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
