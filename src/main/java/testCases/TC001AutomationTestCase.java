package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001AutomationTestCase extends BaseClass {

    @Test(groups = {"Master", "Sanity"})
    public void verifyAccountRegistration() {

        try {
            logger.info("******** Starting TC001AutomationTestCase ********");

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount link");

            hp.clickRegister();
            logger.info("Clicked on Register link");

            AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

            logger.info("Providing customer details...");
            regPage.setFirstName(randomeString().toUpperCase());
            regPage.setLastName(randomeString().toUpperCase());
            regPage.setEmail(randomeString() + "@gmail.com");
            regPage.setTelephone(randomeNumber());

            String password = randomAlphaNumeric();
            regPage.setPassword(password);
            regPage.setConfirmPassword(password);
            regPage.setPrivacyPolicy();
            regPage.clickContinue();

            logger.info("Validating confirmation message...");
            String confirmationMsg = regPage.getConfirmationMsg();
            Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!");

            logger.info("******** Finished TC001AutomationTestCase ********");

        } catch (Exception e) {
            logger.error("Test failed: " + e.getMessage());
            logger.debug("Debug details: ", e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }
    }
}
