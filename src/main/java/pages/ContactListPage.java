package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ContactListPage {

    protected WebDriver driver;
    private WebDriverWait wait;

    // ===== Locators =====
    private By contactListTable = By.id("contact-list");
    private By pageHeader = By.xpath("//h1[text()='Contact List']");
    private By addContactButton = By.xpath("//button[contains(text(),'Add a New Contact')]");
    private By logoutButton = By.xpath("//button[text()='Logout']");
    private By tableHeaders = By.xpath("//table//th");

    // ===== Constructor =====
    public ContactListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =====================================================
    // ===== Existing Method (Login tests use this)
    // =====================================================
    public boolean isContactListDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactListTable));
        return driver.findElement(contactListTable).isDisplayed();
    }

    // ========================
    // ===== UI VALIDATIONS =====
    // ========================
    public boolean isPageHeaderDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
        return driver.findElement(pageHeader).isDisplayed();
    }

    public boolean isAddContactButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addContactButton));
        return driver.findElement(addContactButton).isDisplayed();
    }

    public boolean isLogoutButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        return driver.findElement(logoutButton).isDisplayed();
    }

    public int getTableHeaderCount() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableHeaders));
        return driver.findElements(tableHeaders).size();
    }

    public List<String> getTableHeaderTexts() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableHeaders));
        List<WebElement> headers = driver.findElements(tableHeaders);
        return headers.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    // ========================
    // ===== ACTION METHODS =====
    // ========================
    public void clickAddContact() {
        wait.until(ExpectedConditions.elementToBeClickable(addContactButton)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    // ========================
    // ===== CONTACT METHODS =====
    // ========================

    // Check if contact exists
    public boolean isContactPresent(String fullName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactListTable));
        By contactNameLocator = By.xpath("//table//td[text()='" + fullName + "']");
        List<WebElement> elements = driver.findElements(contactNameLocator);
        return elements.size() > 0;
    }

    // Select contact for editing/viewing
    public void selectContact(String fullName) {
        By contactNameLocator = By.xpath("//table//td[text()='" + fullName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(contactNameLocator)).click();
    }

    // Delete contact
    public void deleteContact(String fullName) {
        By deleteBtnLocator = By.xpath(
                "//table//td[text()='" + fullName + "']/following-sibling::td/button[contains(text(),'Delete')]");
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtnLocator)).click();
    }
}
