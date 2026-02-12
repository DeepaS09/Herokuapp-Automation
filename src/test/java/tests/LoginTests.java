package tests;

import pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;

public class LoginTests extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            // email, password, isSuccess, expectedError
            {"deed@gmail.com", "11!!22@@33", true,  null},
            {"deed@gmail.com", "wrongPass",   false, "Invalid credentials"},
            {"deed@gmail.com", "",            false, "Password is required"},
            {"",               "11!!22@@33",   false, "Email is required"},
            {"",               "",             false, "Email and password are required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password, boolean isSuccess, String expectedError) {

        HomePage home = new HomePage(driver);
        home.clickLogin();

        LoginPage login = new LoginPage(driver);
        login.login(email, password);

        if (isSuccess) {
            ContactListPage list = new ContactListPage(driver);
            Assert.assertTrue(
                list.isContactListDisplayed(),
                "Contact list should be displayed for valid login"
            );
        } else {
            Assert.assertEquals(
                login.getErrorMessage(),
                expectedError
            );
        }
    }
}
