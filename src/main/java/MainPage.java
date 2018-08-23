import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);

    }

    void NavigateToHomepage(){
        this.driver.navigate().to("http://automationpractice.com");

    }
}
