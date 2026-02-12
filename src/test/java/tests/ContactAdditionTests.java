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

public class ContactAdditionTests extends BaseTest {

    private AddContactPage addContactPage;

    @BeforeMethod
    public void loginAndNavigateToAddContact() {
        HomePage home = new HomePage(driver);
        home.clickLogin();

        LoginPage login = new LoginPage(driver);
        login.login("deed@gmail.com", "11!!22@@33");

        ContactListPage contactListPage = new ContactListPage(driver);
        contactListPage.clickAddContact();

        addContactPage = new AddContactPage(driver);
    }

    @DataProvider(name = "validContactData")
    public Object[][] validContactData() {
        return new Object[][]{
                {"John", "Doe", "john.doe" + System.currentTimeMillis() + "@mail.com", "8005551234", "1986-05-20"},
                {"Jane", "Smith", "jane.smith" + System.currentTimeMillis() + "@mail.com", "", "1990-12-01"},
                {"Alice", "Brown", "alice.brown" + System.currentTimeMillis() + "@mail.com", "1234567890", ""}
        };
    }

    @Test(dataProvider = "validContactData")
    public void addContactWithMandatoryFields(String firstName, String lastName, String email, String phone, String birthday) {
        addContactPage.addMandatoryFields(firstName, lastName, email);

        if (!phone.isEmpty()) {
            addContactPage.addOptionalPhone(phone);
        }
        if (!birthday.isEmpty()) {
            addContactPage.addInvalidBirthday(birthday);
        }

        addContactPage.saveContact();

        Assert.assertTrue(driver.getCurrentUrl().contains("/contactList"),
                "Did not redirect to Contact List page after saving contact");
    }

    @Test
    public void addContactWithoutMandatoryFieldsShowsError() {
        addContactPage.saveContact();

        Assert.assertTrue(driver.getCurrentUrl().contains("/addContact"),
                "Expected to stay on Add Contact page due to validation errors");
    }
}
