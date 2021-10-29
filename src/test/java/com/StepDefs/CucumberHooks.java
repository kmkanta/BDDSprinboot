package com.StepDefs;

import com.BDDCucumber.Annotations.LazyAutowired;
import com.BDDCucumber.Testbase.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.context.ApplicationContext;


public class CucumberHooks extends TestBase {

    @LazyAutowired
    private ApplicationContext ctx;

    @AfterStep
    public void afterStep(Scenario scenario){
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName();
            byte[] sourcePath = ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }

    @After
    public void afterScenario(){

        //closeBrowser();
    }
}
