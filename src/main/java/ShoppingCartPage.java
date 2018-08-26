import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {

    private By selector;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }
 void PressProceedToCheckout(By Selector){
     selector = Selector;

     driver.findElement(selector).click();

 }




}
