import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T06_Dropdown_Static_Dynamic {

    WebDriver driver;


    @BeforeMethod
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Browsers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(priority = 1)
    public void staticList() throws InterruptedException {
    driver.get("https://the-internet.herokuapp.com/dropdown");

    // 1- Define WebElement for the Dropdown List
        WebElement list = driver.findElement(By.id("dropdown"));

    // 2- Create new object from Select class
        Select droplist = new Select(list);

    // 3- Select the options using 3 methods
        // 3.1 - SelectByIndex
        Thread.sleep(2000);
        droplist.selectByIndex(1);

        Thread.sleep(2000);
        droplist.selectByIndex(2);

       //3.2 - SelectByValue
        Thread.sleep(2000);
        droplist.selectByValue("1");

        Thread.sleep(2000);
        droplist.selectByValue("2");

      // 3.3 - SelectByVisibleText
        Thread.sleep(2000);
        droplist.selectByVisibleText("Option 1");

        Thread.sleep(2000);
        droplist.selectByVisibleText("Option 2");

    }

    @Test (priority = 2)
    public void dynamicList() throws InterruptedException {
        driver.get("https://www.google.com/");
        // 1- Search
        driver.findElement(By.name("q")).sendKeys("lgbtqia");

        // 2- Select an option from the dynamic dropdown list - by using findElementS
        //Thread.sleep(3000);

        WebDriverWait waitobj = new WebDriverWait(driver, Duration.ofSeconds(20));
        //waitobj.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("li[class=\"sbct\"]")))).isDisplayed();

        List<WebElement> list = driver.findElements(By.cssSelector("li[class=\"sbct\"]"));            //11 webElementS
        list.get(8).click();
    }

    @AfterMethod
    public void quitBrowser() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();

    }
}
