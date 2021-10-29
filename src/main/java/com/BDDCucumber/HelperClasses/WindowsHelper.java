package com.BDDCucumber.HelperClasses;

import com.BDDCucumber.Annotations.LazyAutowired;
import com.BDDCucumber.Annotations.PageFragment;
import com.BDDCucumber.Testbase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.context.ApplicationContext;

@PageFragment
public class WindowsHelper extends TestBase {

    @LazyAutowired
    private ApplicationContext ctx;

    public void switchByTitle(final String title){
        try {
            WebDriver driver = this.ctx.getBean(WebDriver.class);
            driver.getWindowHandles()
                    .stream()
                    .map(handle -> driver.switchTo().window(handle).getTitle())
                    .filter(t -> t.startsWith(title))
                    .findFirst();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("No Such Window");
        }
    }
    public void switchByIndex(final int index){
        WebDriver driver = this.ctx.getBean(WebDriver.class);
        String[] handles = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(handles[index]);
        //this.wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    }


}
