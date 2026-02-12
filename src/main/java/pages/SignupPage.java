package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {

    protected WebDriver driver;
    private WebDriverWait wait;

    // Header
    private By addUserHeader = By.xpath("//h1[text()='Add User']");
    private By firstNameInput = By.id("firstName");
    private By lastNameInput  = By.id("lastName");
    private By emailInput     = By.id("email");
    private By passwordInput  = By.id("password");

    private By submitButton = By.id("submit");
    private By errorMessage = By.id("error");

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ---------- Page validations ----------

    public boolean isAddUserPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addUserHeader));
        return driver.findElement(addUserHeader).isDisplayed();
    }

    public boolean areAllFieldsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        return driver.findElement(firstNameInput).isDisplayed()
                && driver.findElement(lastNameInput).isDisplayed()
                && driver.findElement(emailInput).isDisplayed()
                && driver.findElement(passwordInput).isDisplayed()
                && driver.findElement(submitButton).isDisplayed();
    }

    // ---------- User actions ----------

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    // ---------- Messages / results ----------

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText();
    }

    public boolean isSignupSuccessful() {
        wait.until(ExpectedConditions.urlContains("/contactList"));
        return driver.getCurrentUrl().contains("/contactList");
    }
}
