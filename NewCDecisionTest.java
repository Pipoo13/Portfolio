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

import java.time.Duration;


public class NewCDecisionTest {
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
        driver.get("http://127.0.0.1:8000/court_decisions/create"); // Add your actual login URL here
        
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
    public void NewArticle() throws InterruptedException {
                    // Input Title
                    driver.findElement(By.name("title")).sendKeys("Sample Court Decision");

                    // Input Decision Number
                    driver.findElement(By.name("decision_number")).sendKeys("123456");
        
                    // Input Trial Type and Abbreviation
                    driver.findElement(By.name("trial_type")).sendKeys("Civil");
                    driver.findElement(By.name("trial_type_abbreviation")).sendKeys("CIV");
        
                    // Input Trial Place
                    driver.findElement(By.name("trial_place")).sendKeys("Jakarta");
        
                    // Select Date Read
                    driver.findElement(By.name("date_read")).sendKeys("2023-12-01"); // Use format YYYY-MM-DD
        
                    // Input Source
                    driver.findElement(By.name("source")).sendKeys("Law Journal");
        
                    // Input Law Field
                    driver.findElement(By.name("law_field")).sendKeys("Criminal Law");
        
                    // Input Subject
                    driver.findElement(By.name("subject")).sendKeys("Theft Case");
        
                    // Select Decision Status
                    new Select(driver.findElement(By.name("decision_status"))).selectByVisibleText("Tetap");
        
                    // Select Language
                    new Select(driver.findElement(By.name("language"))).selectByVisibleText("Indonesia");
        
                    // Select Document Status
                    new Select(driver.findElement(By.name("document_status"))).selectByVisibleText("Published");
        
                    // Select Court Decision Category
                    new Select(driver.findElement(By.name("court_decision_category_id"))).selectByVisibleText("Putusan MA");
        
                    // Select Uploaded By
                    new Select(driver.findElement(By.name("uploaded_by"))).selectByVisibleText("Dr. Meredith Erdman");
        
                    // Input TEU Entity
                    driver.findElement(By.name("teu_entity")).sendKeys("Some Legal Entity");



        // Upload Document File (first document)
        WebElement documentInput = driver.findElement(By.name("documents[]"));
        documentInput.sendKeys("C:\\Users\\acer\\Dropbox\\PC\\Downloads\\5869-16185-1-PB.pdf"); // Replace with your document path
        new Select(driver.findElement(By.name("document_types[]"))).selectByVisibleText("Indonesia");

        // Click 'Add Document' button to add another document upload section
        WebElement buttonAdd = driver.findElement(By.xpath("/html/body/div/main/div/form/div[9]/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonAdd);

        // Wait briefly for the new document section to load (adjust as needed)
        Thread.sleep(1000);

        // Locate the new file input and upload the second document
        WebElement newDocumentInput = driver.findElements(By.name("documents[]")).get(1); // Second file input
        newDocumentInput.sendKeys("C:\\Users\\acer\\Dropbox\\PC\\Downloads\\632-61-4659-1-10-20211123.pdf"); // Replace with your document path
        Select newDocumentTypeSelect = new Select(driver.findElements(By.name("document_types[]")).get(1)); // Second select
        newDocumentTypeSelect.selectByVisibleText("English");

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