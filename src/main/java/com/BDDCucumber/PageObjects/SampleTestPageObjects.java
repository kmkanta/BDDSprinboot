package com.BDDCucumber.PageObjects;

import com.BDDCucumber.Annotations.Page;
import com.BDDCucumber.Testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Page
public class SampleTestPageObjects extends TestBase {

    @FindBy(xpath = "//input[@placeholder='Example: john@gmail.com']")
    private WebElement Login_UserName;

    @FindBy(id = "password")
    private WebElement Login_Password;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement Login_Continue;

    @FindBy(xpath = "//a[normalize-space()='Admin']")
    private WebElement LoginAs_Admin;

    @FindBy(xpath = "//*[@id='referralSub']/ul/li[1]")
    private WebElement MR_Referrals;

    @FindBy(xpath = "//*[@id='referralSub']/ul/li[2]")
    private WebElement MR_Patients;

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> patientName;

    @FindBy(xpath = "")
    private WebElement element;

public void loginAsAdmin(String email){
    Login_UserName.sendKeys(email);
    Login_Password.sendKeys("Ftp@1234");
    Login_Continue.click();
    staticWait(5);
    LoginAs_Admin.click();
    staticWait(5);
}

public boolean getTableContent(String expectedValue){
    boolean flag=false;
    MR_Referrals.click();
    staticWait(4);
    int rowCount = driver.findElements(By.xpath("//table/tbody/tr")).size();
    int columnCount = driver.findElements(By.xpath("//table/tbody/tr[1]/td")).size();
    for(int i=1;i<rowCount;i++){
        for(int j=1;j<=columnCount;j++){
          String actualValue = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+j+"]")).getText();
          if(actualValue.contains(expectedValue)){
              flag = true;
              System.out.println(actualValue+" and "+expectedValue);
              System.out.println(i+":"+j);
              Assert.assertEquals(actualValue,expectedValue);
              break;
          }
        }
    }
    if(flag){
        return true;
    }
    return flag;
    }

    public void getTableRows(String actualData){
        List<WebElement> eleList = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
        List<String> patientList = eleList.stream().map(s -> s.getText()).collect(Collectors.toList());
        System.out.println("**********"+patientList);
        List<String> details = eleList.stream().filter(s -> s.getText().equals(actualData)).
                map(s -> getDetails(s)).collect(Collectors.toList());
        details.forEach(text-> System.out.println(text));
    }
    public String getDetails(WebElement s) {
        String patientDetails = s.findElement(By.xpath("/following-sibling::td")).getText();
    return  patientDetails;
    }

}
