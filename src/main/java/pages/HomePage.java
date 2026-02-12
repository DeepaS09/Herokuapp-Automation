package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    protected WebDriver driver;

    private By signupButton = By.id("signup");
    private By loginButton  = By.id("login");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Signup
    public boolean isSignupVisible() {
        return driver.findElement(signupButton).isDisplayed();
    }

    public void clickSignup() {
        driver.findElement(signupButton).click();
    }

    // Login
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}
