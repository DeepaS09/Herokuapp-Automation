package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditContactPage {

    private WebDriver driver;

    // Locators
    private By firstName = By.id("firstName");
    private By lastName  = By.id("lastName");
    private By phone     = By.id("phone");
    private By saveBtn   = By.id("save");

    // ✅ REQUIRED CONSTRUCTOR
    public EditContactPage(WebDriver driver) {
        this.driver = driver;
    }

    // Update methods
    public void updateFirstName(String newFirstName) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(newFirstName);
    }

    public void updateLastName(String newLastName) {
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(newLastName);
    }

    public void updatePhone(String newPhone) {
        driver.findElement(phone).clear();
        driver.findElement(phone).sendKeys(newPhone);
    }

    public void clickSave() {
        driver.findElement(saveBtn).click();
    }
}
