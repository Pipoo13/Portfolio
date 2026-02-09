package sipehukum;

import java.time.Duration;
import java.time.LocalDate;

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

public class EditPublicationTest {
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
        driver.get("http://127.0.0.1:8000/publications/"); // Add your actual login URL here
        
        // Wait for login form elements and login
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"loginForm\"]/div/div/div/button")));
        
        username.sendKeys("superadmin@example.com");
        password.sendKeys("qwerty123");
        login.click();

        driver.findElement(By.cssSelector("a.btn.btn-warning")).click();
       
    }

    @Test
    public void eeditPublication() throws InterruptedException{
        // Fill the title field
        driver.findElement(By.name("title")).sendKeys("Sample Publication Title");

        // Fill the content field
        driver.findElement(By.cssSelector("div.note-editable")).sendKeys("This is the content of the sample publication.");

        // Select today's date for publication
        WebElement publishDate = driver.findElement(By.name("publish_date"));
        publishDate.sendKeys(LocalDate.now().toString());

        // Select a category
        driver.findElement(By.name("category")).sendKeys("Artikel");

        // Select a status
        driver.findElement(By.name("status")).sendKeys("Published");

        // Upload a cover image
        WebElement coverImage = driver.findElement(By.name("cover_image"));
        coverImage.sendKeys("C:\\Users\\acer\\Pictures\\cropped 2.jpeg"); // Update this path to an actual image

        // Upload additional photos
        WebElement additionalPhotos = driver.findElement(By.name("photos[]"));
        additionalPhotos.sendKeys("C:\\Users\\acer\\Pictures\\w.jpeg"); // Update with paths to actual images

        // Handle dropdowns
        Select categorySelect = new Select(driver.findElement(By.name("category")));
        categorySelect.selectByValue("Artikel");

        Select statusSelect = new Select(driver.findElement(By.name("status")));
        statusSelect.selectByValue("published");

        }
    


    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
