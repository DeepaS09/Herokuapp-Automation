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

public class ContactDeletionTests extends BaseTest {

    private ContactListPage contactList;

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

    @DataProvider(name = "contactsToDelete")
    public Object[][] contactsToDelete() {
        return new Object[][]{
                {"Mark", "Taylor"},
                {"John", "Doe"},
                {"Alice", "Brown"}
        };
    }

    @Test(dataProvider = "contactsToDelete")
    public void deleteContactAndVerify(String firstName, String lastName) {

        // Step 1: Add contact
        contactList.clickAddContact();
        AddContactPage addContact = new AddContactPage(driver);
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase()
                + System.currentTimeMillis() + "@mail.com";
        addContact.addMandatoryFields(firstName, lastName, email);
        addContact.saveContact();

        // Step 2: Delete contact
        String fullName = firstName + " " + lastName;
        contactList.deleteContact(fullName);

        // Step 3: Verify deletion
        Assert.assertFalse(contactList.isContactPresent(fullName),
                "Deleted contact is still displayed in Contact List");
    }
}
