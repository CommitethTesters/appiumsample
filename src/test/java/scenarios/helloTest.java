package scenarios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.ChatView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by home on 3/25/2017.
 */
public class helloTest {
    AndroidDriver<WebElement> driver;

    @Before
    public  void Setup() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "im.status.ethereum");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "im.status.ethereum.MainActivity");

        cap.setCapability("avd", "Nexus_4_API_19");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);



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

    @Test
    public void helloTest()
    {
        ChatView console = new ChatView(driver);
        console.continueForRootedDevice();
        console.enterPassword("password");
        //add delay
        console.enterPassword("password");

    }

}
