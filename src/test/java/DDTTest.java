import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(value= Parameterized.class)
public class DDTTest extends BaseTest {
    @Parameterized.Parameter(0)
    public String targetPage;
    @Parameterized.Parameter(1)
    public String targetPageTitle;



    @Parameterized.Parameters(name= "{index}: Open social link for {0} and expect title with {1}")

    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"facebook", "facebook"},
                {"twitter", "twitter"},
                {"youtube", "youtube"},
                {"google-plus", "google"}
        });
    }

@Test
public void AllSocialLinksShouldWorkTest(){
    mainPage.clickSocialLink(targetPage);
    mainPage.SwitchToNewWindow();
    Assert.assertTrue("Page title doesn't contains Selenium Framework in title", Driver.getDriver().getTitle().contains("Selenium Framework"));
    Assert.assertTrue("Page is not a " + targetPageTitle+ " Page", Driver.getDriver().getCurrentUrl().contains(targetPageTitle));
}


}
