package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.ChatView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by home on 3/25/2017.
 */
public class helloTest {
    static AndroidDriver<WebElement> driver;

    @BeforeClass
    public static void Setup() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "im.status.ethereum");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "im.status.ethereum.MainActivity");

        cap.setCapability("avd", "Nexus_4_API_19");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


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
     //   driver.quit();
    }

    @Test
    public void helloTest()
    {
        ChatView console = new ChatView(driver);
        console.continueForRootedDevice();
        console.enterPassword("password");
    }

}