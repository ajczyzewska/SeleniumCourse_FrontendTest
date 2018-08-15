
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
public class First_Test {
    @Before


    @Test
    public void openPageTest() {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com");
        Assert.assertEquals("Wrong page title", "My Store", driver.getTitle());
        driver.quit();
    }


    @Test
    public void testSearchInput() {
        // Test if search input works and how many elements are found
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com");
        String cssSelector1 = "input[name='search_query']";
        driver.findElement(By.cssSelector(cssSelector1)).sendKeys("Printed dress");
        driver.findElement(By.cssSelector("button[name='submit_search']")).click();
        int elementsCount = driver.findElements(By.cssSelector("div[class='product-image-container']")).size();
        Assert.assertEquals("Wrong number of items searched", 5, elementsCount);
    }


    @Test
    public void testElementsOnWebpage_v1() {
        // Search for containers, and in foreach of elements findelement and look for text
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        //Looking for containers
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com");
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
   public void ImplicitWaitTest(){
        // Excercise Find all printed dress on webpage and check if selected one is added to cart properly
       System.setProperty("webdriver.chrome.driver", "chromedriver");

       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       driver.navigate().to("http://automationpractice.com");
       String cssSelector_SearchQuery = "input[name='search_query']";
       driver.findElement(By.cssSelector(cssSelector_SearchQuery)).sendKeys("Printed dress");
       driver.findElement(By.cssSelector("button[name='submit_search']")).click();

       //Navigating to selected Dress
       String cssSelector_ChangeToList = "li[id='list']";
       driver.findElement(By.cssSelector(cssSelector_ChangeToList)).click();
       String cssSelector_productLink = "div[class*='button-container'] a[data-id-product='5']";
       driver.findElement(By.cssSelector(cssSelector_productLink)).click();
       String cssSelector_CartOverlay = "div[class='layer_cart_overlay']";
       driver.findElement(By.cssSelector(cssSelector_CartOverlay));
       Assert.assertTrue("Element is not visible", driver.findElement(By.cssSelector(cssSelector_CartOverlay)).isDisplayed());




   }



    @Test
    public void ExplicitWaitTest() {
        // Excercise: Find all printed dress on webpage and check if selected one is added to cart properly, select specific size and colour

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com");
        String cssSelector4 = "input[name='search_query']";
        driver.findElement(By.cssSelector(cssSelector4)).sendKeys("Printed dress");
        driver.findElement(By.cssSelector("button[name='submit_search']")).click();

        //Navigating to selected Dress
        String cssSelector_productLink = "ul[class= 'product_list grid row'] h5  a[href*='id_product=5']";
        driver.findElement(By.cssSelector(cssSelector_productLink)).click();

        // Adding to cart
        String cssSelector_AddToCart = "p[id='add_to_cart'] button[type='Submit']";
        driver.findElement(By.cssSelector(cssSelector_AddToCart)).click();
        driver.findElement(By.cssSelector("span[title='Close window']")).isDisplayed();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("span[title='Close window']"))));
        driver.navigate().to("http://automationpractice.com/index.php?controller=order");

        //Assert
        String cssSelector_AddedToCart = "small[class='cart_ref']";
        String Cart= driver.findElement(By.cssSelector(cssSelector_AddedToCart)).getText();
        Assert.assertTrue("Element is not displayed", driver.findElement(By.cssSelector(cssSelector_AddedToCart)).isDisplayed());
        Assert.assertTrue( "SKU content is not the SKU expected", Cart.contains("demo_5"));


    }


    @Test
    public void MustCountCorrectValueOfSpecialOfferTest() {

        // Excercise:  Find category Dresses and check if Sale price is correct according to sale procents

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        // Looking for list of dresses
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://automationpractice.com");
        driver.findElement(By.xpath(".//ul[contains(@class,'sf-menu clearfix menu-content sf-js-enabled sf-arrows')]/li[2]")).click();
        List<WebElement> ListOfPricesWithSpecialOffer  = driver.findElements(By.xpath(".//div[contains(@class, 'product-container')]/*/div[contains(@class,'content_price')]/span[contains(@class,'old-price product-price')]/ancestor::div[contains(@class,'content_price')]"));

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
        boolean isSpecialPriceCountedCorrectly= false;

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

                if (contentPriceValue == contentRegularPriceValue*(100-pricePercentReductionValue)/100){
                    isSpecialPriceCountedCorrectly= true;
                }


        }
        Assert.assertTrue("One of the Values is not counted properly", isSpecialPriceCountedCorrectly);
    }
    }

