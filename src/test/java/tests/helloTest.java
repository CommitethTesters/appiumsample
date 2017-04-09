package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.beanutils.PropertyUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.ChatView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import io.appium.java_client.AppiumDriver;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.LinkedList;

/**
 * Created by home on 3/25/2017.
 */
public class HelloTest {
    static AndroidDriver<WebElement> driver;

    public static final String USERNAME = System.getenv("SAUCE_USERNAME");
    public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    public static final String buildTag = System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER");


    @BeforeClass
    public static void SetupRemote() throws MalformedURLException {

        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("appiumVersion", "1.6.3");
        caps.setCapability("deviceName","Samsung Galaxy S4 Emulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "");
        caps.setCapability("platformVersion","4.4");
        caps.setCapability("platformName","Android");

        caps.setCapability("build", buildTag);
        //read url to apk file from Jenkins param
        String apkUrl = System.getProperty("apkUrl");

        caps.setCapability("app", apkUrl);
      //  caps.setCapability("app", "http://artifacts.status.im:8081/artifactory/nightlies-local/im.status.ethereum-7b89ad.apk");

        driver = new AndroidDriver<WebElement>(new URL(URL), caps);
        printSessionId();
    }


    @AfterClass
    public static void teardown(){
        //close the app
        driver.quit();
    }

    @Test
    public void canRunAppTest()
    {
        ChatView console = new ChatView(driver);
        console.continueForRootedDevice();
        console.verifyPasswordRequestIsVisible();
    }

    //@Test
    public void canCreatePasswordTest()
    {
        ChatView console = new ChatView(driver);
        console.continueForRootedDevice();
        console.createPassword("password");
        console.verifyPasswordIsSet();
    }

    static private void printSessionId() {

        String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
                (((RemoteWebDriver) driver).getSessionId()).toString(), buildTag);
        System.out.println(message);
    }

}