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


    @BeforeClass
    public static void SetupRemote() throws MalformedURLException {

        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("appiumVersion", "1.6.3");
        caps.setCapability("deviceName","Samsung Galaxy S4 Emulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "");
        caps.setCapability("platformVersion","4.4");
        caps.setCapability("platformName","Android");

        //read url to apk file from Jenkins param
        String apkUrl = System.getProperty("apk_url");

        caps.setCapability("app", apkUrl);
      //  caps.setCapability("app", "http://artifacts.status.im:8081/artifactory/nightlies-local/im.status.ethereum-7b89ad.apk");

        driver = new AndroidDriver<WebElement>(new URL(URL), caps);

    }

    public static void SetupLocal() throws MalformedURLException {

       /* DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "im.status.ethereum");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "im.status.ethereum.MainActivity");

        cap.setCapability("avd", "Nexus_4_API_19");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
*/

        /////////
      /*  File appDir = new File("D:\\Users\\home\\IdeaProjects\\myFirstAppiumProject\\apps");
        File app = new File(appDir, "im.status.ethereum-de41bd.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device","Android");
        //mandatory capabilities
        capabilities.setCapability("deviceName","Android");
        capabilities.setCapability("platformName","Android");
        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
*/
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

    @Test
    public void canCreatePasswordTest()
    {
        ChatView console = new ChatView(driver);
        console.continueForRootedDevice();
        console.createPassword("password");
        console.verifyPasswordIsSet();
    }

}