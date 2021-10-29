package com.BDDCucumber.Testbase;

import com.BDDCucumber.Annotations.LazyAutowired;
import com.BDDCucumber.Annotations.PageFragment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.Assert;

import javax.annotation.PostConstruct;
import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@PageFragment
public class TestBase {

    private static final Logger log = Logger.getLogger(TestBase.class.getName());

    @LazyAutowired
    public RemoteWebDriver driver;

    @LazyAutowired
    public WebDriverWait wait;

    @LazyAutowired
    private ResourceLoader loader;

    @LazyAutowired
    private ApplicationContext ctx;

    @PostConstruct
    public void init(){
        PageFactory.initElements(this.driver, this);
    }

    @Value("${url}")
    private String url;

    @Value("${fileName}")
    private String fileName;

    public void getData(String property){
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(loader.getResource("classpath:data/"+fileName+".properties"));
            System.out.println(properties.getProperty(property));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String data) {
        log.info(data);
       // Reporter.log(data);
       // test.log(LogStatus.INFO, data);
    }
    public void enterURL(){
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        driver.get(url);
        log("Browser launched");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void getTitle(String expectedTitle){
        System.out.println(driver.getTitle());
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    public void closeBrowser(){
        driver.quit();
    }

    public String captureScreen(String fileName)  {
        if (fileName == "") {
            fileName = "blank";
        }
        File destFile = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        File scrFile = this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/BDDCucumber/ScreenShots/";
            destFile = new File(
                    (String) reportDirectory + fileName + "_" + formatter.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile.toString();
    }

    public void staticWait(int seconds){
        try{
            Thread.sleep(1000*seconds);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
