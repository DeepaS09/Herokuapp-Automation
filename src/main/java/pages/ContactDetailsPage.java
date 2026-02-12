package pages;


import org.openqa.selenium.*;

	public class ContactDetailsPage {

	    WebDriver driver;

	    By editEmail = By.id("editEmail");
	    By saveBtn   = By.id("saveEdit");
	    By deleteBtn = By.id("delete");
	    By confirmBtn = By.id("confirmDelete");

	    public ContactDetailsPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void editEmail(String newEmail) {
	        WebElement field = driver.findElement(editEmail);
	        field.clear();
	        field.sendKeys(newEmail);
	        driver.findElement(saveBtn).click();
	    }

	    public void deleteContact() {
	        driver.findElement(deleteBtn).click();
	        driver.findElement(confirmBtn).click();
	    }
	}

