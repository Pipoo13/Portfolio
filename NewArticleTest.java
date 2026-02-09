package sipehukum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;


public class NewArticleTest {
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
        driver.get("http://127.0.0.1:8000/law_articles/create"); // Add your actual login URL here
        
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
    public void NewArticle() {
        driver.findElement(By.name("title")).sendKeys("Test Law Article Title");
        driver.findElement(By.name("release_place")).sendKeys("Jakarta");
        driver.findElement(By.name("release_year")).sendKeys("2024");
        driver.findElement(By.name("source")).sendKeys("Legal Journal");
        driver.findElement(By.name("law_field")).sendKeys("Constitutional Law");
        driver.findElement(By.name("location_stored")).sendKeys("Library Section A");
        driver.findElement(By.name("teu_entity")).sendKeys("Supreme Court");
        driver.findElement(By.name("subject")).sendKeys("Constitutional Rights");

        // Handle dropdowns
        Select languageSelect = new Select(driver.findElement(By.name("language")));
        languageSelect.selectByValue("Indonesia");

        Select statusSelect = new Select(driver.findElement(By.name("document_status")));
        statusSelect.selectByValue("published");

        Select categorySelect = new Select(driver.findElement(By.name("law_article_category_id")));
        categorySelect.selectByVisibleText("Artikel Hukum");

        Select uploadedBySelect = new Select(driver.findElement(By.name("uploaded_by")));
        uploadedBySelect.selectByValue("1"); // Super Admin

        // Handle file upload
        String filePath = new File("C:\\Users\\acer\\Dropbox\\PC\\Documents\\NOTULENSI PRESENTASI TREE SIREG2A.pdf").getAbsolutePath();
        WebElement fileInput = driver.findElement(By.name("documents[]"));
        fileInput.sendKeys(filePath);


        // Select document type
        Select documentTypeSelect = new Select(driver.findElement(By.name("document_types[]")));
        documentTypeSelect.selectByValue("Indonesia");

        // Submit the form
        WebElement button = driver.findElement(By.xpath("/html/body/div/main/div/form/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }
    
    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}