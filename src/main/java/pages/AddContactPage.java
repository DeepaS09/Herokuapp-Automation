	package pages;
	import org.openqa.selenium.*;
	
	public class AddContactPage {
		
	
	
	
		    WebDriver driver;
	
		    By firstName = By.id("firstName");
		    By lastName  = By.id("lastName");
		    By email     = By.id("email");
		    By phone     = By.id("phone");
		    By birthday  = By.id("birthday");
		    By saveBtn   = By.id("save");
	
		    public AddContactPage(WebDriver driver) {
		        this.driver = driver;
		    }
	
		    public void addMandatoryFields(String f, String l, String e) {
		        driver.findElement(firstName).sendKeys(f);
		        driver.findElement(lastName).sendKeys(l);
		        driver.findElement(email).sendKeys(e);
		    }
	
		    public void addOptionalPhone(String p) {
		        driver.findElement(phone).sendKeys(p);
		    }
	
		    public void addInvalidBirthday(String date) {
		        driver.findElement(birthday).sendKeys(date);
		    }
	
		    public void saveContact() {
		        driver.findElement(saveBtn).click();
		    }
		}
	
	
