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

public class productsTest {
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
    public void test_add_product_to_cart() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.doLogin("standard_user","secret_sauce");
        softAssert.assertEquals(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).getText(), "ADD TO CART", "Valid User Login FAILED");
        products addBackpack = new products(driver);
        addBackpack.addToCart();
    }

    @Test
    public void test_remove_product_from_cart() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.doLogin("standard_user","secret_sauce");
        softAssert.assertTrue(driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).isDisplayed(), "Valid User Login FAILED");
        products backpack = new products(driver);
        backpack.addToCart();
        backpack.removeFromCart();
    }

    @Test
    public void test_sorting_products() throws InterruptedException {
        loginPage loginValidUser = new loginPage(driver);
        loginValidUser.doLogin("standard_user","secret_sauce");
        softAssert.assertTrue(driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).isDisplayed(), "Valid User Login FAILED");
        products list = new products(driver);
        list.sorting();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
