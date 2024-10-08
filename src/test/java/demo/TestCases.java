package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCases {
    ChromeDriver driver;
    // WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(TestCases.class.getName());

    @Test
    public void testCase01() throws InterruptedException {

        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        Thread.sleep(3000);

        WebElement nameInputBox = driver.findElement(By.xpath("//div[@class='Xb9hP']/input[@type='text']"));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Xb9hP']/input[@type='text']")));
        System.out.println("wait 1");
        Thread.sleep(3000);
        Wrappers.enterText(nameInputBox, "Crio Learner");

        WebElement practicingAutomationTextArea = driver.findElement(By.xpath("//textarea[contains(@class,'tL9Q4c')]"));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@class,'tL9Q4c')]")));
        String practisingAutomationText = "I want to be the best QA Engineer!";

        String epochTimeString = Wrappers.getEpochTimeAsString();
        Thread.sleep(6000);
        System.out.println("wait 2");
        Wrappers.enterText(practicingAutomationTextArea, practisingAutomationText + " " + epochTimeString);
        Thread.sleep(3000);
        System.out.println("wait 3");
        // Select radio button a/c to automation experience
        Wrappers.radioButton(driver, "0 - 2");
        Thread.sleep(3000);

        // Select checkbox for skillsets
        System.out.println("wait 4");
        Wrappers.checkBox(driver, "Java");
        Wrappers.checkBox(driver, "Selenium");
        Wrappers.checkBox(driver, "TestNG");

        // Clicking on dropdown to select Salutation

        WebElement dropDoWebElement = driver.findElement(By.xpath("//div[contains(@class,'DEh1R')]"));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@jsname='LgbsSe']")));
        System.out.println("wait 5");
        Thread.sleep(3000);
        Wrappers.clickOnElement(driver, dropDoWebElement);
        Thread.sleep(2000);
        Wrappers.dropDownClick(driver, "Mrs");
        Thread.sleep(3000);

        // Enter 7 days ago date

        WebElement dateInputBox = driver.findElement(By.xpath("//input[@type='date']"));
        String sevenDaysAgoDate = Wrappers.getdateSevenDaysAgo();
        Thread.sleep(30000);
        System.out.println("wait 6");
        Wrappers.enterText(dateInputBox, sevenDaysAgoDate);

        WebElement hourInput = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement minuteInput = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        WebElement submitButton = driver.findElement(By.xpath("//div[@class='lRwqcd']/div"));

        Wrappers.enterText(hourInput, "07");
        Wrappers.enterText(minuteInput, "30");
        Wrappers.clickOnElement(driver, submitButton);

        Thread.sleep(5000);
        System.out.println("wait 7");

        WebElement successMsg = driver.findElement(By.xpath("//div[@class='vHW8K']"));
        String text = successMsg.getText();
        System.out.println(text);
        Thread.sleep(5000);

    }

    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        // driver = new ChromeDriver(options);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}