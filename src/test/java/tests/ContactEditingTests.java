package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddContactPage;
import pages.ContactListPage;
import pages.EditContactPage;
import pages.HomePage;
import pages.LoginPage;

public class ContactEditingTests extends BaseTest {

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

    // ========================
    // ===== Data Provider =====
    // ========================
    @DataProvider(name = "editContactData")
    public Object[][] editContactData() {
        return new Object[][]{
                // {originalFirstName, originalLastName, newFirstName, newPhone}
                {"Mark", "Taylor", "Michael", "9998887777"},
                {"John", "Doe", "Jonathan", "8887776666"},
                {"Alice", "Brown", "Alicia", "7776665555"}
        };
    }

    // ========================
    // ===== Test Method =====
    // ========================
    @Test(dataProvider = "editContactData")
    public void editContactAndVerify(String originalFirstName, String originalLastName,
                                     String updatedFirstName, String updatedPhone) {

        // Step 1: Add contact first
        contactList.clickAddContact();
        AddContactPage addContact = new AddContactPage(driver);

        String email = originalFirstName.toLowerCase() + "." + originalLastName.toLowerCase()
                + System.currentTimeMillis() + "@mail.com";

        addContact.addMandatoryFields(originalFirstName, originalLastName, email);
        addContact.saveContact();

        // Step 2: Select added contact
        contactList = new ContactListPage(driver);
        String fullName = originalFirstName + " " + originalLastName;
        contactList.selectContact(fullName);

        // Step 3: Edit contact
        EditContactPage editPage = new EditContactPage(driver);
        editPage.updateFirstName(updatedFirstName);
        editPage.updatePhone(updatedPhone);
        editPage.clickSave();

        // Step 4: Verify updated contact appears in Contact List
        contactList = new ContactListPage(driver);
        String updatedFullName = updatedFirstName + " " + originalLastName;

        Assert.assertTrue(contactList.isContactPresent(updatedFullName),
                "Updated contact is NOT displayed in Contact List");
    }
}
