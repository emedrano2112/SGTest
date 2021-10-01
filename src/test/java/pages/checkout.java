package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class checkout {

    WebDriver driver;
    public SoftAssert softAssert;
    WebDriverWait wait;

    //Locators
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By zip = By.id("postal-code");
    By continueBtn = By.id("continue");
    By cancelBtn = By.id("cancel");
    By backpackInCart = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]");
    By finish = By.id("finish");

    public checkout(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    public void validateRequired(String Name, String lastname, String zipcode)
    {
        softAssert = new SoftAssert();
        wait = new WebDriverWait(driver, 15);

        driver.findElement(lastName).sendKeys(lastname);
        driver.findElement(zip).sendKeys(zipcode);
        driver.findElement(continueBtn).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("data-test=\"error\"")));
        softAssert.assertEquals(driver.findElement(By.tagName("data-test=\"error\"")).getText(), "Error: First Name is required");

        driver.findElement(firstName).sendKeys(Name);
        driver.findElement(zip).sendKeys(zipcode);
        driver.findElement(continueBtn).click();
        softAssert.assertEquals(driver.findElement(By.tagName("data-test=\"error\"")).getText(), "Error: Last Name is required");

        driver.findElement(firstName).sendKeys(Name);
        driver.findElement(lastName).sendKeys(lastname);
        driver.findElement(continueBtn).click();
        softAssert.assertEquals(driver.findElement(By.tagName("data-test=\"error\"")).getText(), "Error: Postal Code is required");
        softAssert.assertAll();
    }

    public void cancelCheckout()
    {
        softAssert = new SoftAssert();
        driver.findElement(cancelBtn).click();
        softAssert.assertTrue(driver.findElement(backpackInCart).isDisplayed(), "Product is CANCELED with success");
        softAssert.assertAll();
    }

    public void checkoutProcess(String Name, String lastname, String zipcode)
    {
        softAssert = new SoftAssert();
        driver.findElement(firstName).sendKeys(Name);
        driver.findElement(lastName).sendKeys(lastname);
        driver.findElement(zip).sendKeys(zipcode);
        driver.findElement(continueBtn).click();
        driver.findElement(finish).click();
        softAssert.assertEquals(driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText(), "THANK YOU FOR YOUR ORDER");
        softAssert.assertAll();
    }
}
