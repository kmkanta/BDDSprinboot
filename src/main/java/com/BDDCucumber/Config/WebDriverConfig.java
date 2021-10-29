package com.BDDCucumber.Config;

import com.BDDCucumber.Annotations.LazyConfig;
import com.BDDCucumber.Annotations.ThreadScopeBean;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@LazyConfig
public class WebDriverConfig {

    @Value("${timeout:30}")
    private int timeout;


    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriverWait webDriverWait(RemoteWebDriver driver){
        return new WebDriverWait(driver,this.timeout);
    }


    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public RemoteWebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public RemoteWebDriver firefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    public RemoteWebDriver edgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    @Value("${userName}")
    private String userName;

    @Value("${accessKey}")
    private String accessKey;


    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "sauceLab")
    public RemoteWebDriver sauceLabs() throws Exception {
        DesiredCapabilities caps = DesiredCapabilities.iphone();
        caps.setCapability("appiumVersion", "1.20.1");
        caps.setCapability("deviceName", "iPhone 8 Simulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("platformVersion", "14.3");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("browserName", "Safari");
        caps.setCapability("idleTimeout", "90");
        caps.setCapability("newCommandTimeout", "90");
        caps.setCapability("locationServicesEnabled", false);
        return new RemoteWebDriver(new URL("https://" + userName + ":" + accessKey
                + "@ondemand.us-west-1.saucelabs.com:443/wd/hub"), caps);
    }

    @Value("${deviceName}")
    private String deviceName;

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "chromeEmulator")
    public RemoteWebDriver chromeEmulator() throws Exception {
        WebDriverManager.chromedriver().setup();
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", deviceName);
        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(capabilities);
    }
//For Default browser execution
    @ThreadScopeBean
    @ConditionalOnMissingBean
    public RemoteWebDriver ieDriver() {
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }

}
