package tests;

  import base.BaseTest;
	import pages.*;
	import org.testng.Assert;
	import org.testng.annotations.Test;

	public class LogoutTests extends BaseTest {

	    @Test
	    public void logoutTest() {
	        new LoginPage(driver).login("valid@mail.com", "password123");

	        ContactListPage list = new ContactListPage(driver);
	        list.logout();

	        Assert.assertTrue(driver.getTitle().contains("Login"));
	    }
	}

