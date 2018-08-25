import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {

    By EmailSelector = By.xpath("//form[contains(@id, 'login_form')]//div[contains(@class,'form-group')]//input[contains(@id, 'email')]");
    By PasswordSelector = By.xpath("//form[contains(@id, 'login_form')]//div[contains(@class,'form-group')]//input[contains(@id, 'passwd')]");




    public SignInPage(WebDriver driver) {
        super(driver);
    }

    void FillEmailInput(String email){
        driver.findElement(EmailSelector).sendKeys(email);
    }

    void FillPasswordInput(String password){

        driver.findElement(PasswordSelector).sendKeys(password);
    }

}
