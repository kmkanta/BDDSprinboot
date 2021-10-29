package com.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features  ="classpath:features",
        //For executing multiple tags: "@Tag1 or @teg2 or @tag3", "@tag1 and @tag2", "not@tag1"
        tags = ("@Login1"),
        glue = {"com.StepDefs","com.BDDCucumber.Config"},
        plugin = {"pretty","json:target/cucumber.json",
                            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                            "timeline:test-output-thread/"
                },
        dryRun = false,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true,
        publish = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
