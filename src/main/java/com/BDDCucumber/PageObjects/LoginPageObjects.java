package com.BDDCucumber.PageObjects;

import com.BDDCucumber.Annotations.Page;
import com.BDDCucumber.Testbase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Page
public class LoginPageObjects extends TestBase {


    @FindBy(xpath = "//*[@class='icon-label user-icon-label']")
    private WebElement signInLink;

    @FindBy(name = "userId")
    private WebElement name;

    @FindBy(name = "password")
    private WebElement password;

    public WebElement getSignInLink() {
        return signInLink;
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getPassword() {
        return password;
    }

    public void clickOnSignInLink(){
        getSignInLink().click();
    }
    public void enterEmail(String email){
        getName().sendKeys(email);
    }

    public void enterPassword(String password){
        getPassword().sendKeys(password);
    }

    public void login(String username, String password){
        signInLink.click();
        name.sendKeys(username);
        this.password.sendKeys(password);
        staticWait(4);
        getTitle("Welcome to TruckPro");
        captureScreen("LoginScreen");
    }
}
