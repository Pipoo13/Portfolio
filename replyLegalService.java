package sipehukum;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class replyLegalService {
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
        driver.get("http://127.0.0.1:8000/legal-services/9d92ffc4-f3cc-453a-bbd5-e1931653efc1/reply"); // Add your actual login URL here

        // Wait for login form elements and login
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"loginForm\"]/div/div/div/button")));
        
        username.sendKeys("superadmin@example.com");
        password.sendKeys("qwerty123");
        login.click();
        
       
    }

    @Test
    public void reply() throws InterruptedException{
         // Fill out the Name field
         WebElement nameField = driver.findElement(By.name("reply_message"));
         nameField.sendKeys("Baiklah itu bisa dilaporakan dengan dokumen ini");

         // Upload a file (optional field)
         WebElement fileInput = driver.findElement(By.id("attachment"));
         fileInput.sendKeys("C:\\Users\\acer\\Dropbox\\PC\\Downloads\\Pamflet kajian #2.jpg"); // Replace with your file path

         // Click the Submit button
         WebElement submitButton = driver.findElement(By.cssSelector(".btn.btn-primary.mt-2"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
              
        }
    

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
