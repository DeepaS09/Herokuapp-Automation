package pages;

import org.openqa.selenium.*;

public class LoginPage {

    WebDriver driver;

    By email    = By.id("email");
    By password = By.id("password");
    By loginBtn = By.id("loginSubmit");
    By errorMsg = By.id("error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {
        driver.findElement(email).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }
}
