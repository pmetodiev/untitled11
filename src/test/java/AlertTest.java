import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class AlertTest {
    WebDriver driver;
    @BeforeMethod
    public static void setUp()
    {


        WebDriver driver= new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://pragmatic.bg/automation/lecture13waits/Alerts.html");
    }

    @Test
    public void testSimpleAlert()
    {
        //Clicking button will show a simple Alert with OK Button
        WebElement button = driver.findElement(By.id("simple"));
        button.click();

        try {

            //Get the Alert
            Alert alert = driver.switchTo().alert();

            //Get the Text displayed on Alert using getText() method of Alert class
            String textOnAlert = alert.getText();

            //Click OK button, by calling accept() method of Alert Class
            alert.accept();

            //Verify Alert displayed correct message to user
            assertEquals(textOnAlert, "Hello! I am an alert box!");

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConfirmAccept()
    {
        //Clicking button will show a Confirmation Alert with OK and Cancel Button
        WebElement button = driver.findElement(By.id("confirm"));
        button.click();

        try {

            //Get the Alert
            Alert alert = driver.switchTo().alert();

            //Click OK button, by calling accept() method of Alert Class
            alert.accept();

            //Verify Page displays correct message on Accept
            WebElement message = driver.findElement(By.id("demo"));
            assertEquals(message.getText(), "You Accepted Alert!");

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConfirmDismiss()
    {
        //Clicking button will show a Confirmation Alert with OK and Cancel Button
        WebElement button = driver.findElement(By.id("confirm"));
        button.click();

        try {

            //Get the Alert
            Alert alert = driver.switchTo().alert();

            //Click Cancel button, by calling dismiss() method of Alert Class
            alert.dismiss();

            //Verify Page displays correct message on Dismiss
            WebElement message = driver.findElement(By.id("demo"));
            assertEquals(message.getText(), "You Dismissed Alert!");

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrompt()
    {
        //Clicking button will show a Prompt Alert asking user to enter
        //value/text with OK and Cancel Button
        WebElement button = driver.findElement(By.id("prompt"));
        button.click();


        try {

            //Get the Alert
            Alert alert = driver.switchTo().alert();

            //Enter some value on Prompt by calling sendKeys() method of Alert Class
            alert.sendKeys("Foo");

            //Click OK button, by calling accept() method of Alert Class
            alert.accept();

            //Verify Page displays message with value entered in Prompt
            WebElement message = driver.findElement(By.id("prompt_demo"));
            assertEquals(message.getText(), "Hello Foo! How are you today?");
            //WaitTool.waitForElementRefresh(driver, By.id("bla"), 20);
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public static void tearDown()
    {
        driver.quit();
    }
}
