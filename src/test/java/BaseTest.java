import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    WebDriver driver;
    MainPage mainPage;
    FooterPage footerPage;
    ShoppingCartPage shoppingCartPage;
    SignInPage signInPageForTest;

    @Before

    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        footerPage = new FooterPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        signInPageForTest= new SignInPage(driver);
    }


    @After
    public void quitDriver(){
        driver.quit();
    }
}
