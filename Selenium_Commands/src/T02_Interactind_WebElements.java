import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T02_Interactind_WebElements {

    WebDriver driver;

    @BeforeMethod
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Browsers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
    }

    @Test(priority = 0)
    public void Browser() throws InterruptedException {
      driver.findElement(By.name("q")).sendKeys("LGBTQIA");
      Thread.sleep(3000);

      driver.findElement(By.name("q")).clear();
        Thread.sleep(2000);

      driver.findElement(By.name("q")).sendKeys("Work as a QA in Netherlands");
        Thread.sleep(2000);

      driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @AfterMethod
    public void quitBrowser() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();

    }
}
