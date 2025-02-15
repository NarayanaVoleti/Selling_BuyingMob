package tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected IOSDriver driver;
    protected AppiumDriverLocalService service;
    @BeforeClass
    public void startServer(){
        service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//Mind-Graph//AppData//Roaming//npm//node_modules//appium//build//lib//main.js")) .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();
    }

    @BeforeMethod
    public void setCapabilities() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setApp("/Users/ballamudi/Downloads/MySG.app "); //Need to change
        options.setPlatformName("iOS");
        options.setPlatformVersion("18.1"); // check version
        options.setUdid("24A60693-F965-460A-BDCB-C070F1D79119"); //Need to change udId
        options.setDeviceName("iPhone 16 Pro"); //Nneed to change Ddevice Nname
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @AfterClass
    public void stopServer(){
        if(service!=null)
            service.stop();
    }
}
