import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class First_Test extends BaseTest {

    @Test
    public void openPageTest() {
        Assert.assertEquals("Wrong page title", "My Store", driver.getTitle());
    }


    @Test
    public void testSearchInput() {
        // Test if search input works and how many elements are found
        mainPage.NavigateToHomepage();
        String cssSelector1 = "input[name='search_query']";
        driver.findElement(By.cssSelector(cssSelector1)).sendKeys("Printed dress");
        mainPage.ClickElementByCssSelector(By.cssSelector("button[name='submit_search']"));
        int elementsCount = driver.findElements(By.cssSelector("div[class='product-image-container']")).size();
        Assert.assertEquals("Wrong number of items searched", 5, elementsCount);
    }


    @Test
    public void testElementsOnWebpage_v1() {
        //Looking for containers
        mainPage.NavigateToHomepage();
        String cssSelector2 = "ul[id='homefeatured']>li";
        int elementsCount2 = driver.findElements(By.cssSelector(cssSelector2)).size();
        System.out.println(elementsCount2);

        // Iterating through containers
        String cssSelector3 = "a[class= 'product-name']";
        List<WebElement> ListOfWebElements = driver.findElements(By.cssSelector(cssSelector2));
        boolean dressCondition = false;
        int sizeOfList = ListOfWebElements.size();
        for (int i = 0; i < sizeOfList; i++) {
            String productName = ListOfWebElements.get(i).findElement(By.cssSelector(cssSelector3)).getText();
            if (productName.contains("Dress")) {
                dressCondition = true;
                break;
            } else dressCondition = false;
        }
        Assert.assertTrue("Dress is not available on webpage", dressCondition);
    }


    @Test
    public void testElementsOnWebpage_v2() {

        // TODO - look for word "Dress"
    }

    // ----------------------- WAIT() in Selenium -----------------------------------------------------------------
    @Test
    public void ImplicitWaitTest() {
        // Excercise Find all printed dress on webpage and check if selected one is added to cart properly
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        mainPage.NavigateToHomepage();
        String cssSelector_SearchQuery = "input[name='search_query']";
        driver.findElement(By.cssSelector(cssSelector_SearchQuery)).sendKeys("Printed dress");
        mainPage.ClickElementByCssSelector(By.cssSelector("button[name='submit_search']"));

        //Navigating to selected Dress
        String cssSelector_ChangeToList = "li[id='list']";
        mainPage.ClickElementByCssSelector(By.cssSelector(cssSelector_ChangeToList));
        String cssSelector_productLink = "div[class*='button-container'] a[data-id-product='5']";
        mainPage.ClickElementByCssSelector(By.cssSelector(cssSelector_productLink));
        String cssSelector_CartOverlay = "div[class='layer_cart_overlay']";
        driver.findElement(By.cssSelector(cssSelector_CartOverlay));
        Assert.assertTrue("Element is not visible", driver.findElement(By.cssSelector(cssSelector_CartOverlay)).isDisplayed());


    }


    @Test
    public void ExplicitWaitTest() {
        // Excercise: Find all printed dress on webpage and check if selected one is added to cart properly, select specific size and colour
        mainPage.NavigateToHomepage();
        String cssSelector4 = "input[name='search_query']";
        driver.findElement(By.cssSelector(cssSelector4)).sendKeys("Printed dress");
        mainPage.ClickElementByCssSelector(By.cssSelector("button[name='submit_search']"));

        //Navigating to selected Dress
        String cssSelector_productLink = "ul[class= 'product_list grid row'] h5  a[href*='id_product=5']";
        mainPage.ClickElementByCssSelector(By.cssSelector(cssSelector_productLink));

        // Adding to cart
        String cssSelector_AddToCart = "p[id='add_to_cart'] button[type='Submit']";
        mainPage.ClickElementByCssSelector(By.cssSelector(cssSelector_AddToCart));
        driver.findElement(By.cssSelector("span[title='Close window']")).isDisplayed();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("span[title='Close window']"))));
        driver.navigate().to("http://automationpractice.com/index.php?controller=order");

        //Assert
        String cssSelector_AddedToCart = "small[class='cart_ref']";
        String Cart = driver.findElement(By.cssSelector(cssSelector_AddedToCart)).getText();
        Assert.assertTrue("Element is not displayed", driver.findElement(By.cssSelector(cssSelector_AddedToCart)).isDisplayed());
        Assert.assertTrue("SKU content is not the SKU expected", Cart.contains("demo_5"));


    }


    @Test
    public void MustCountCorrectValueOfSpecialOfferTest() {

        // Excercise:  Find category Dresses and check if Sale price is correct according to sale procents

        // Looking for list of dresses
        mainPage.NavigateToHomepage();
        driver.findElement(By.xpath(".//ul[contains(@class,'sf-menu clearfix menu-content sf-js-enabled sf-arrows')]/li[2]")).click();
        List<WebElement> ListOfPricesWithSpecialOffer = driver.findElements(By.xpath(".//div[contains(@class, 'product-container')]/*/div[contains(@class,'content_price')]/span[contains(@class,'old-price product-price')]/ancestor::div[contains(@class,'content_price')]"));

        //Looking for containers that have Special Offer
        int sizeOfList = ListOfPricesWithSpecialOffer.size();
        String contentPricePercentReductionXPath = ".//span[contains(@class,'price-percent-reduction')]";
        System.out.println(driver.findElement(By.xpath(contentPricePercentReductionXPath)).getText());

        String contentPriceXpath = ".//span[contains(@class,'price product-price')]";
        String contentRegularPriceXpath = ".//span[contains(@class,'old-price product-price')]";
        String pricePercentReduction;
        int pricePercentReductionValue;
        String contentPrice;
        float contentPriceValue;
        String contentRegularPrice;
        float contentRegularPriceValue;
        boolean isSpecialPriceCountedCorrectly = false;

        for (int i = 0; i < sizeOfList; i++) {

            //Get Percent Reduction
            pricePercentReduction = ListOfPricesWithSpecialOffer.get(i).findElement(By.xpath(contentPricePercentReductionXPath)).getText().replaceAll("[^\\d.]+", "");
            pricePercentReductionValue = Integer.parseInt(pricePercentReduction);
            System.out.println("Percent reduction of Container number " + i + " : " + pricePercentReductionValue);

            // Get Price after Special Offer
            contentPrice = ListOfPricesWithSpecialOffer.get(i).findElement(By.xpath(contentPriceXpath)).getText().replaceAll("[^\\d.]+", "");
            contentPriceValue = Float.parseFloat(contentPrice);
            System.out.println("Product price of product from Container number " + i + " : " + contentPriceValue);

            // Get Regular/Old price
            contentRegularPrice = ListOfPricesWithSpecialOffer.get(i).findElement(By.xpath(contentRegularPriceXpath)).getText().replaceAll("[^\\d.]+", "");
            contentRegularPriceValue = Float.parseFloat(contentRegularPrice);
            System.out.println("Regular/ol price of product from Container number " + i + " : " + contentRegularPriceValue);

            if (contentPriceValue == contentRegularPriceValue * (100 - pricePercentReductionValue) / 100) {
                isSpecialPriceCountedCorrectly = true;
            }


        }
        Assert.assertTrue("One of the Values is not counted properly", isSpecialPriceCountedCorrectly);
    }



    @Test
    public void ShouldNavigateToPasswordInputWhenTabUsedTest(){
        Actions action = new Actions(driver);
        SignInPage singInPage = mainPage.NavigateToSignInPage();
        singInPage.FillEmailInput("zzz@z.com" + Keys.TAB);
        action.sendKeys(Keys.TAB);
        WebElement passwordElement = driver.findElement(singInPage.PasswordSelector);
        WebElement checkPasswordElement= driver.switchTo().activeElement();
        Assert.assertEquals("Different field than Password is selected",checkPasswordElement, driver.findElement(singInPage.PasswordSelector));
    }


    @Test
    public void NavigationQuickViewShouldWorkProperlyTest(){
        this.driver.navigate().to("http://automationpractice.com");
        mainPage.PressQucikViewOnProductPage();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//iframe[@class='fancybox-iframe']"))));
        mainPage.SwitchToIframe();
        mainPage.AddToCart();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(mainPage.AddToCart)));
        By IconOKSuccess = By.cssSelector("i[class='icon-ok']");
        Assert.assertTrue("Successful message not visible",driver.findElement(IconOKSuccess).isDisplayed());
    }


    @Test
    public void FacebookLinkInFooterShouldRedirectToNewWindowTest(){
        this.driver.navigate().to("http://automationpractice.com");
        footerPage.clickSocialLink("facebook");
        mainPage.SwitchToNewWindow();
        Assert.assertTrue("Page title doesn't contains Selenium Framework in title", driver.getTitle().contains("Selenium Framework"));
        Assert.assertTrue("Page is not a Facebook Page", driver.getCurrentUrl().contains("facebook"));
    }

    @Test
    public void TwitterLinkInFooterShouldRedirectToNewWindowTest(){
        this.driver.navigate().to("http://automationpractice.com");
        footerPage.clickSocialLink("twitter");
        mainPage.SwitchToNewWindow();
        Assert.assertTrue("Page title doesn't contains Selenium Framework in title", driver.getTitle().contains("Selenium Framework"));
        Assert.assertTrue("Page is not a Twitter Page", driver.getCurrentUrl().contains("twitter"));
    }

    @Test
    public void YoutubeLinkInFooterShouldRedirectToNewWindowTest(){
        this.driver.navigate().to("http://automationpractice.com");
        footerPage.clickSocialLink("youtube");
        mainPage.SwitchToNewWindow();
        Assert.assertTrue("Page title doesn't contains Selenium Framework in title", driver.getTitle().contains("Selenium Framework"));
        Assert.assertTrue("Page is not a Youtube Page", driver.getCurrentUrl().contains("youtube"));
    }

    @Test
    public void GooglePlusLinkInFooterShouldRedirectToNewWindowTest(){
        this.driver.navigate().to("http://automationpractice.com");
        footerPage.clickSocialLink("google-plus");
        mainPage.SwitchToNewWindow();
        Assert.assertTrue("Page title doesn't contains Selenium Framework in title", driver.getTitle().contains("Selenium Framework"));
        Assert.assertTrue("Page is not a Google Page", driver.getCurrentUrl().contains("google"));
    }

    @Test
    public void ProcessOfBuyingShouldWorkProperlyTest(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        NavigationQuickViewShouldWorkProperlyTest();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a[class='btn btn-default button button-medium']"))));
        shoppingCartPage.PressProceedToCheckout(By.xpath("//span[contains(text(),'Proceed to checkout')]"));
        //driver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]")).click();
        //Raising Quantity
        driver.findElement(By.cssSelector("a[class='cart_quantity_up btn btn-default button-plus']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[size='2']"))));
        shoppingCartPage.PressProceedToCheckout(By.xpath("//a[contains(@class,'button btn btn-default standard-checkout button-medium')]"));
        signInPageForTest.FillEmailInput("zzz@z.com");
        signInPageForTest.FillPasswordInput("zzzzz");
        mainPage.ClickElementByCssSelector(By.cssSelector("button[id='SubmitLogin']"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("li[class='step_current third']"))));
        shoppingCartPage.PressProceedToCheckout(By.cssSelector("button[class='button btn btn-default button-medium']"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("li[class='step_current four']"))));
        mainPage.ClickElementByCssSelector(By.cssSelector("input[name='cgv']"));
        shoppingCartPage.PressProceedToCheckout(By.xpath("//button[contains(@name,'processCarrier')]"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("li[class='step_current last']"))));
        mainPage.ClickElementByCssSelector(By.cssSelector("a[class='bankwire']"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='box cheque-box']"))));
        Assert.assertEquals("Wrong message at the end of buying process", "BANK-WIRE PAYMENT.", driver.findElement(By.cssSelector("h3[class='page-subheading']")).getText());


    }
}

