package tests;

	import base.BaseTest;
	import org.testng.Assert;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	import pages.AddContactPage;
	import pages.ContactListPage;
	import pages.HomePage;
	import pages.LoginPage;

	public class ContactDisplayTests extends BaseTest {

	    private ContactListPage contactList;
	    private AddContactPage addContactPage;

	    @BeforeMethod
	    public void login() {
	        HomePage home = new HomePage(driver);
	        home.clickLogin();

	        LoginPage login = new LoginPage(driver);
	        login.login("deed@gmail.com", "11!!22@@33");

	        contactList = new ContactListPage(driver);

	        Assert.assertTrue(contactList.isContactListDisplayed(),
	                "Login failed - Contact List not displayed");
	    }

	    // ========================
	    // ===== Data Provider =====
	    // ========================

	    @DataProvider(name = "contactData")
	    public Object[][] contactData() {
	        return new Object[][]{
	                {"John", "Doe"},
	                {"Jane", "Smith"},
	                {"Alice", "Brown"}
	        };
	    }

	    // ========================
	    // ===== Test Method =====
	    // ========================

	    @Test(dataProvider = "contactData")
	    public void verifyAddedContactIsDisplayed(String firstName, String lastName) {

	        String email = firstName.toLowerCase() + "." + lastName.toLowerCase()
	                + System.currentTimeMillis() + "@mail.com";

	        // Navigate to Add Contact page
	        contactList.clickAddContact();
	        addContactPage = new AddContactPage(driver);

	        // Add contact
	        addContactPage.addMandatoryFields(firstName, lastName, email);
	        addContactPage.saveContact();

	        // Reinitialize ContactListPage after navigation
	        contactList = new ContactListPage(driver);

	        // Verify contact appears in table
	        String fullName = firstName + " " + lastName;

	        Assert.assertTrue(contactList.isContactPresent(fullName),
	                "Newly added contact is NOT displayed in Contact List");
	    }
	}


