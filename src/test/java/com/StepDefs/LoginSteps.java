package com.StepDefs;

import com.BDDCucumber.Annotations.LazyAutowired;
import com.BDDCucumber.PageObjects.LoginPageObjects;
import com.BDDCucumber.Testbase.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginSteps extends TestBase {

    @LazyAutowired
    private LoginPageObjects loginPage;

    @Given("I launch url with chrome browser")
    public void iLaunchUrlWithChromeBrowser() {
        enterURL();
    }

    @And("I enter email address")
    public void iEnterEmailAddress() {
        loginPage.clickOnSignInLink();
        staticWait(5);
        loginPage.enterEmail("Mani@gmail.com");
    }

    @And("I enter password")
    public void iEnterPassword() {
        loginPage.enterPassword("Mani@143");
    }

    @And("I click on submit button")
    public void iClickOnSubmitButton() {
        log("Click on Submit Button");
    }

    @Then("I verify Login is success")
    public void iVerifyLoginIsSuccess() {
        log("Login is Success");
    }

    @And("I close the browser")
    public void iCloseTheBrowser() {
        staticWait(5);
        closeBrowser();
    }

    @Given("I launch url with {string} browser")
    public void iLaunchUrlWithBrowser(String arg0) {
        enterURL();
    }

    @And("I enter email address as {string}")
    public void iEnterEmailAddressAs(String email) {
        loginPage.clickOnSignInLink();
        staticWait(5);
        loginPage.enterEmail(email);
    }

    @And("I enter password as {string}")
    public void iEnterPasswordAs(String password) {
        loginPage.enterPassword(password);
    }


    @And("I print the {int}")
    public void iPrintThe(int count) {
        log("Count is :"+count);
    }
}
