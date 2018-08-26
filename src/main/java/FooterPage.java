import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterPage extends BasePage {


    public FooterPage(WebDriver driver) {
        super(driver);
    }

    void clickSocialLink(String SocialName){
        By SocialSelector=By.cssSelector("li[class='"+SocialName+"']>a");
        driver.findElement(SocialSelector).click();
    }
}
