package sipehukum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import junit.framework.Assert;

public class NewAdminTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    private void init() {
        // initiate browser
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Initialize wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Navigate to login page
        driver.get("http://127.0.0.1:8000/login"); // Add your actual login URL here
        
        // Wait for login form elements and login
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"loginForm\"]/div/div/div/button")));
        
        username.sendKeys("superadmin@example.com");
        password.sendKeys("qwerty123");
        login.click();
        
        // Wait for navigation menu to be visible after login
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("/html/body/div/header/nav/div/ul/li[5]/span/a"))).click();
            
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"adminCollapse\"]/ul/li[1]/a"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("/html/body/div/main/div/div[1]/a"))).click();
    }

    private void fillFormField(String name, String value) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        element.clear();
        element.sendKeys(value);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void NewAdmin() {
        // Wait for form to be visible and fill in the required fields
        fillFormField("name", "Test User");
        fillFormField("first_name", "Test");
        fillFormField("last_name", "User");
        fillFormField("email", "testuserslah@example.com");
        fillFormField("phone_number", "1234567890");
        fillFormField("password", "Password123");
        fillFormField("confirm_password", "Password123");

        // Select PNS Rank with wait
        WebElement pnsRankElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("pns_rank")));
        Select pnsRankSelect = new Select(pnsRankElement);
        pnsRankSelect.selectByValue("Non-PNS");

        // Select Institution with wait
        WebElement institutionElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("institution_id")));
        Select institutionSelect = new Select(institutionElement);
        institutionSelect.selectByIndex(1);

        // Select Status with wait
        WebElement statusElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("status")));
        Select statusSelect = new Select(statusElement);
        statusSelect.selectByValue("active");

        // Select Role with wait
        WebElement roleCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("role-2")));
        if (!roleCheckbox.isSelected()) {
            roleCheckbox.click();
        }

        // Submit the form with wait
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("/html/body/div/main/div/div[2]/div/div/div/form/button")));
        submitButton.click();

        // Wait for redirect and verify URL
        wait.until(ExpectedConditions.urlToBe("http://127.0.0.1:8000/users"));
        String actualUrl = "http://127.0.0.1:8000/users";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}