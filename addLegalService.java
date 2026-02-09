package sipehukum;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class addLegalService {
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
        driver.get("http://127.0.0.1:8000/pengaduan/create"); // Add your actual login URL here
        
       
    }

    @Test
    public void DelPublication() throws InterruptedException{
         // Fill out the Name field
         WebElement nameField = driver.findElement(By.id("name"));
         nameField.sendKeys("John Doe");

         // Fill out the Email field
         WebElement emailField = driver.findElement(By.id("email"));
         emailField.sendKeys("afifhaidar35@gmail.com");

         // Fill out the Message field
         WebElement messageField = driver.findElement(By.id("message"));
         messageField.sendKeys("This is a test inquiry about legal services.");

         // Upload a file (optional field)
         WebElement fileInput = driver.findElement(By.id("attachment"));
         fileInput.sendKeys("C:\\Users\\acer\\Dropbox\\PC\\Downloads\\Pamflet kajian #2.jpg"); // Replace with your file path

         // Click the Submit button
         WebElement submitButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
              
        }
    

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
