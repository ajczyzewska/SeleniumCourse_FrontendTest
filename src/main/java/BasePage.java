import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected WebDriverWait wait;
    protected WebDriver driver;

    public BasePage(WebDriver driver){
            this.driver = driver;
            wait = new WebDriverWait(driver, 30);
        }

        protected void provideInput(By inputBy, CharSequence value){
            WebElement input = driver.findElement(inputBy);
            input.clear();
            input.sendKeys(value);
        }

        protected void clickOnElement(By elementBy){
            driver.findElement(elementBy).click();
        }

        protected WebElement findElement(By elementBy){
            return driver.findElement(elementBy);
        }

        protected List<WebElement> findElements(By elementBy){
            return driver.findElements(elementBy);
        }
    }

