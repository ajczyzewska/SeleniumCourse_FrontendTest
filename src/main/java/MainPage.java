import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    protected  By AddToCart = By.cssSelector("p[id='add_to_cart']>button[name='Submit']");
    public MainPage(WebDriver driver) {
        super(driver);

    }

    void NavigateToHomepage(){
        this.driver.navigate().to("http://automationpractice.com");

    }

    SignInPage NavigateToSignInPage(){
        this.driver.navigate().to("http://automationpractice.com");
        this.driver.findElement(By.xpath("//a[@class='login']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[contains(@class, 'is_required validate account_input form-control')]"))));
        return new SignInPage(driver);
    }

    void PressQucikViewOnProductPage()  {

        By ProductImage = By.xpath("//li[contains(@class,'first-in-line')]/div[contains(@class,'product-container')]/div[contains(@class,'left-block')]/div[contains(@class,'product-image-container')]");
        By QuickViewButton = By.xpath("//a[@class='quick-view']");
        Actions action = new Actions(driver);
        WebElement ProductImage_WebElement = driver.findElement(ProductImage);
        WebElement QuickVievBUtton_WebElement = driver.findElement(QuickViewButton);
        action.moveToElement(ProductImage_WebElement).moveToElement(QuickVievBUtton_WebElement).click().build().perform();
    }

    void SwitchToIframe(){
        WebElement quickview_frame = driver.findElement(By.className("fancybox-iframe"));
        driver.switchTo().frame(quickview_frame);
    }

    void AddToCart(){

        driver.findElement(AddToCart).click();
    }

}
