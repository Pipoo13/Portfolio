package sipehukum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import junit.framework.Assert;

public class loginTest {

    WebDriver driver;

    @BeforeTest
    private void init(){
        // Initiate browser
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        // Go to homepage
        driver.navigate().to("http://127.0.0.1:8000/login");
        driver.manage().window().maximize();
    }

    @SuppressWarnings("deprecation")
    @Test(priority = 1)
    public void LoginWithNullValue() {
        // Find the login button within the test method
        WebElement login = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div/div/button"));
        
        // Attempt to click login without entering any input
        login.click();

        // Locate the email field to verify if it's required
        WebElement email = driver.findElement(By.id("email"));
        Assert.assertTrue(Boolean.parseBoolean(email.getAttribute("required")));
    }

    @SuppressWarnings("deprecation")
    @Test(priority = 2)
    public void LoginWithWrongValue() {
        WebElement login = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div/div/button"));
        WebElement username = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        
        // Enter incorrect credentials
        username.sendKeys("loyo@example.com");
        password.sendKeys("qwerty");
        
        // Attempt login
        login.click();

        // Verify the error message
        String errorMessage = driver.findElement(By.className("btn-danger")).getText();
        Assert.assertEquals(errorMessage, "Email atau kata sandi salah. Percobaan login tersisa: 2");
    }

    @SuppressWarnings("deprecation")
    @Test(priority = 3)
    public void Login() {
        WebElement login = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div/div/button"));
        WebElement username = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));

        // Enter correct credentials
        username.sendKeys("superadmin@example.com");
        password.sendKeys("qwerty123");

        // Attempt login
        login.click();

        // Verify redirection to the dashboard
        String actualUrl = "http://127.0.0.1:8000/dashboard";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
