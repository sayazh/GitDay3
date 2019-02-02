import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    public void loginTest1() {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test" + Keys.ENTER);
        Assert.assertEquals(driver.getTitle(), "Web Orders");
    }
    @Test
    public void negativeLogin() {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        Assert.assertTrue(driver.getTitle().equals("Web Orders Login"));
        String currentUrl = driver.getCurrentUrl();
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Test");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("Test" + Keys.ENTER);
        Assert.assertTrue(driver.getTitle().equals("Web Orders Login"));
        Assert.assertTrue(driver.getCurrentUrl().equals(currentUrl));
    }

    @AfterMethod
    public void cleanUp(){
        driver.close();
    }
}
