package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContactListPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;

public class ContactListTests extends BaseTest {

    private ContactListPage contactList;

    // ---------------- Data Provider ----------------
    @DataProvider(name = "loginCredentials")
    public Object[][] loginCredentials() {
        return new Object[][] {
                {"deed@gmail.com", "11!!22@@33"}
                // You can add more users here
                // {"user2@mail.com", "Password2"},
                // {"user3@mail.com", "Password3"}
        };
    }

    // ---------------- Helper method ----------------
    private void login(String email, String password) {
        HomePage home = new HomePage(driver);
        home.clickLogin();

        LoginPage login = new LoginPage(driver);
        login.login(email, password);

        contactList = new ContactListPage(driver);
        Assert.assertTrue(contactList.isPageHeaderDisplayed(),
                "Contact List page is not displayed after login");
    }

    // ---------------- Tests ----------------

    @Test(dataProvider = "loginCredentials")
    public void verifyContactListPageUI(String email, String password) {
        login(email, password);

        Assert.assertTrue(contactList.isPageHeaderDisplayed(), "Contact List header not displayed");
        Assert.assertTrue(contactList.isAddContactButtonDisplayed(), "Add New Contact button not displayed");
        Assert.assertTrue(contactList.isLogoutButtonDisplayed(), "Logout button not displayed");
        Assert.assertEquals(contactList.getTableHeaderCount(), 7, "Unexpected number of table columns");
    }

    @Test(dataProvider = "loginCredentials")
    public void verifyTableHeaders(String email, String password) {
        login(email, password);

        List<String> expectedHeaders = Arrays.asList(
                "Name",
                "Birthdate",
                "Email",
                "Phone",
                "Address",
                "City, State/Province, Postal Code",
                "Country"
        );

        List<String> actualHeaders = contactList.getTableHeaderTexts();
        Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers do not match expected values");
    }

    @Test(dataProvider = "loginCredentials")
    public void verifyAddContactNavigation(String email, String password) {
        login(email, password);

        contactList.clickAddContact();
        Assert.assertTrue(driver.getCurrentUrl().contains("/addContact"),
                "Did not navigate to Add Contact page after clicking button");
    }

    @Test(dataProvider = "loginCredentials")
    public void verifyLogoutFunctionality(String email, String password) {
        login(email, password);

        contactList.logout();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "Logout did not redirect to login page");
    }
}
