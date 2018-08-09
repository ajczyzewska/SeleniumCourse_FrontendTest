import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Array;

public class First_Test {
    @Before


    @Test
    public void openPageTest(){

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com");
        Assert.assertEquals ("Wrong page title", "My Store", driver.getTitle());
        driver.quit();
    }


    @Test
    public void testSearchInput(){
        // Test if search input works and how many elements are found
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com");
        String cssSelector1 = "input[name='search_query']";
        driver.findElement(By.cssSelector(cssSelector1)).sendKeys("Printed dress");
        driver.findElement(By.cssSelector("button[name='submit_search']")).click();
        int elementsCount= driver.findElements(By.cssSelector("div[class='product-image-container']")).size();
        Assert.assertEquals("Wrong number of items searched",5, elementsCount );
    }
}
