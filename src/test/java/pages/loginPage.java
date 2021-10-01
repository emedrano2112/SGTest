package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {

    WebDriver driver;

    //Locators
    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");

    public loginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    public void doLogin(String user, String pwd)
    {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginBtn).click();
    }

    public void requiredFieldsUsername(String pwd) {
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginBtn).click();
    }

    public void requiredFieldsPassword(String user) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(loginBtn).click();
    }
}
