package qageekweek.openproject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestAddTask {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    @Test
    public void testSearchForCheeseInGoogle() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.name("q"));  // By.cssSelector("textarea[name='q']")
        searchBox.sendKeys("Cheese");
        WebElement buttonElement = driver.findElement(By.name("btnI"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonElement));
        buttonElement.click();
        WebElement resultElement = driver.findElement(By.xpath("//h3/span[contains(.,'Cheese')]"));
        Assert.assertTrue(resultElement.getText().contains("Cheese"));
        TimeUnit.SECONDS.sleep(4);
    }



    @AfterMethod
    public void teardown(){
        if (driver != null) {
            driver.quit();
        }
    }


}
