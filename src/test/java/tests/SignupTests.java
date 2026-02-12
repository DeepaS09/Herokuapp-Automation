package tests;

import base.BaseTest;
import pages.HomePage;
import pages.SignupPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignupTests extends BaseTest {

    SignupPage signup;

    @BeforeMethod
    public void navigateToSignupPage() {
        HomePage home = new HomePage(driver);
        home.clickSignup();
        signup = new SignupPage(driver);

        Assert.assertTrue(signup.isAddUserPageDisplayed(),
                "Add User page is not displayed");
    }

    // ✅ Data Provider for signup scenarios
    @DataProvider(name = "signupData")
    public Object[][] signupData() {
        return new Object[][] {

                // firstName, lastName, email, password, isSuccess

                {"", "", "", "", false},

                {"John", "", "", "", false},

                {"John", "Doe", "", "", false},

                {"John", "Doe", "invalidEmail", "Password@123", false},

                {"John", "Doe",
                        "john" + System.currentTimeMillis() + "@mail.com",
                        "Password@123", true}
        };
    }

    @Test(dataProvider = "signupData")
    public void signupTest(String firstName, String lastName,
                           String email, String password,
                           boolean isSuccess) {

        if (!firstName.isEmpty())
            signup.enterFirstName(firstName);

        if (!lastName.isEmpty())
            signup.enterLastName(lastName);

        if (!email.isEmpty())
            signup.enterEmail(email);

        if (!password.isEmpty())
            signup.enterPassword(password);

        signup.clickSubmit();

        if (isSuccess) {
            Assert.assertTrue(signup.isSignupSuccessful(),
                    "User was not created successfully");
        } else {
            Assert.assertTrue(signup.getErrorMessage().contains("User validation failed"),
                    "Validation error message not displayed as expected");
        }
    }

    // ✅ Keep UI validation separate (not data-driven)
    @Test
    public void verifyAllFieldsAndLabelsAreDisplayed() {
        Assert.assertTrue(signup.areAllFieldsDisplayed(),
                "One or more fields/labels are missing on Add User page");
    }
}
