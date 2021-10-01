package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class products {

    WebDriver driver;
    public SoftAssert softAssert;


    //Locators
    By menu = By.cssSelector("#react-burger-menu-btn");
    By logout = By.id("logout_sidebar_link");
    By backpack = By.id("add-to-cart-sauce-labs-backpack");
    By emptyCart = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    By backpackInCart = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]");
    By fullCart = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");
    By remove = By.id("remove-sauce-labs-backpack");
    By sorter = By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select");
    By sortedBackPack = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]");
    By sortedTShirt = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]");
    By checkout = By.id("checkout");

    public products(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    public void doLogout()
    {
        driver.findElement(menu).click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        driver.findElement(logout).click();
    }

    public void addToCart()
    {
        softAssert = new SoftAssert();
        driver.findElement(backpack).click();
        softAssert.assertNotEquals(driver.findElement(emptyCart), driver.findElement(fullCart));
        driver.findElement(emptyCart).click();
        softAssert.assertTrue(driver.findElement(backpackInCart).isDisplayed(), "Product added SUCCESSFULLY to the cart");
        softAssert.assertAll();
    }

    public void removeFromCart()
    {
        driver.findElement(remove).click();
        softAssert.assertTrue(driver.findElement(emptyCart).isDisplayed(), "Product removed SUCCESSFULLY from the cart");
        softAssert.assertAll();
    }

    public void sorting()
    {
        softAssert = new SoftAssert();
        WebElement selectElement = driver.findElement(sorter);
        Select sorting = new Select(selectElement);
        sorting.selectByVisibleText("Name (Z to A)");
        softAssert.assertEquals(driver.findElement(sortedTShirt), driver.findElement(sortedBackPack));
    }

    public void goToCheckout()
    {
        driver.findElement(checkout).click();
    }
}
