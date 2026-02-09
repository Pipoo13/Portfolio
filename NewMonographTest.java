package sipehukum;

import java.time.Duration;

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

public class NewMonographTest {
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
        driver.get("http://127.0.0.1:8000/legal_monographs/create"); // Add your actual login URL here
        
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
    public void newMonograph() throws InterruptedException{
        driver.findElement(By.name("title")).sendKeys("Sample Monograph Title");
        driver.findElement(By.name("release_place")).sendKeys("Jakarta");
        driver.findElement(By.name("release_year")).sendKeys("2023");
        driver.findElement(By.name("edition")).sendKeys("First Edition");
        driver.findElement(By.name("call_number")).sendKeys("12345");
        driver.findElement(By.name("book_master_number")).sendKeys("98765");
        driver.findElement(By.name("publisher")).sendKeys("Sample Publisher");
        driver.findElement(By.name("physique_description")).sendKeys("200 pages");
        driver.findElement(By.name("law_field")).sendKeys("Criminal Law");
        driver.findElement(By.name("isbn")).sendKeys("978-3-16-148410-0");
        driver.findElement(By.name("exemplar")).sendKeys("5");
        driver.findElement(By.name("location_stored")).sendKeys("Library Shelf 12");
        driver.findElement(By.name("teu_entity")).sendKeys("Legal Entity");
        driver.findElement(By.name("subject")).sendKeys("Legal Studies");


        // Handle dropdowns
        Select languageSelect = new Select(driver.findElement(By.name("language")));
        languageSelect.selectByValue("Indonesia");

        Select statusSelect = new Select(driver.findElement(By.name("document_status")));
        statusSelect.selectByValue("published");

        Select categorySelect = new Select(driver.findElement(By.name("monograph_category_id")));
        categorySelect.selectByVisibleText("Himpunan");

        Select uploadedBySelect = new Select(driver.findElement(By.name("uploaded_by")));
        uploadedBySelect.selectByValue("1"); // Super Admin

        // Upload Document File (first document)
        WebElement documentInput = driver.findElement(By.name("documents[]"));
        documentInput.sendKeys("C:\\Users\\acer\\Dropbox\\PC\\Downloads\\5869-16185-1-PB.pdf"); // Replace with your document path
        new Select(driver.findElement(By.name("document_types[]"))).selectByVisibleText("Indonesia");

        // Click 'Add Document' button to add another document upload section
        WebElement buttonAdd = driver.findElement(By.xpath("/html/body/div/main/div/form/div[10]/button"));
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
