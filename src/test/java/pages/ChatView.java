package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by home on 3/26/2017.
 */
public class ChatView {
    protected AndroidDriver<WebElement> driver;

    public ChatView(AndroidDriver<WebElement> driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }


    @AndroidFindBy(id="android:id/button1")
    public WebElement btnContinue;

    @AndroidFindBy(accessibility="request-password")
    public WebElement requestPassword;

    @AndroidFindBy(accessibility="chat-message-input")
    public WebElement inputChatMessage;

    @AndroidFindBy(accessibility="chat-send-button")
    public WebElement btnSend;


    public void continueForRootedDevice()
    {
        if (isElementPresent(By.id("android:id/button1"))) {
            btnContinue.click();
        }
    }

    public void enterPassword(String password){
        requestPassword.click();
        inputChatMessage.sendKeys(password);
        btnSend.click();

    }


    public boolean isElementPresent(By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (InvalidSelectorException ex) {
            throw ex;
        }
    }

}
