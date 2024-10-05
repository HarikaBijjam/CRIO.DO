package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends Wrappers {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    @Test
    public void testCase01() throws InterruptedException {
        // Navigate to Google Form
        driver.get("https://your-google-form-url");

        // Fill in "Crio Learner" in the first text box
        enterText(By.xpath("//input[@type='text']"), "Crio Learner");

        // Fill in "I want to be the best QA Engineer!" followed by the current epoch
        // time
        long epochTime = Instant.now().getEpochSecond();
        enterText(By.xpath("//textarea[@name='entry.XXXXXXX']"),
                "I want to be the best QA Engineer! " + epochTime);

        // Select "Your Automation Testing experience" from radio buttons
        clickElement(By.xpath("//div[@role='radiogroup']//span[text()='Your Experience']"));

        // Select Java, Selenium, TestNG from checkboxes
        clickElement(By.xpath("//span[text()='Java']"));
        clickElement(By.xpath("//span[text()='Selenium']"));
        clickElement(By.xpath("//span[text()='TestNG']"));

        // Provide how you'd like to be addressed in the next dropdown
        selectDropdown(By.xpath("//select[@name='entry.XXXXXXX']"), "Your Preferred Title");

        // Provide current date minus 7 days in the date field (calculated dynamically)
        LocalDate dateMinus7 = LocalDate.now().minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = dateMinus7.format(formatter);
        enterText(By.xpath("//input[@type='date']"), formattedDate);

        // Provide the time 07:30 in the next field
        enterText(By.xpath("//input[@type='time']"), "07:30");

        // Submit the form
        clickElement(By.xpath("//span[text()='Submit']"));

        // Capture and print success message
        String successMessage = driver.findElement(By.xpath("//div[@class='success']")).getText();
        System.out.println("Form submitted successfully: " + successMessage);

        // Adding sleep to handle potential captcha
        Thread.sleep(5000);
    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
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

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}