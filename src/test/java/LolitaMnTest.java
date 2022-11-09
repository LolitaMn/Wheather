import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LolitaMnTest {

    @Test
    public void testH2TagText_WhenSearchingCtyCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/Users/User/Desktop/Lola/Applications/chromedriver_win32/chromedriver.exe");
        WebDriver driver;
        driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(2000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        //Thread.sleep(5000);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id ='weather-widget']//button[@type='submit']")
        );

        searchButton.click();

        Thread.sleep(2000);
        WebElement parisChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );

        parisChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id ='weather-widget']//h2")
        );

        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();


    }





    @Test
    public void testCheckPageTitle_WhenClickOnGuide() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/Users/User/Desktop/Lola/Applications/chromedriver_win32/chromedriver.exe");
        WebDriver driver;
        driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultURL = "https://openweathermap.org/guide";

        driver.get(url);
        Thread.sleep(2000);


        WebElement guideButton = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href = '/guide']")
        );

        guideButton.click();

        Thread.sleep(2000);


        String actualResultURL = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultURL, expectedResultURL);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

        driver.quit();

    }

    @Test
    public void testCheck_F_TemperatureMeasurement() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/Users/User/Desktop/Lola/Applications/chromedriver_win32/chromedriver.exe");
        WebDriver driver;
        driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String fSymbol = "°F";
        String expectedResult = "°F";

        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(3000);

        WebElement fButton = driver.findElement(
                By.xpath("//div[@class='option' and text()='Imperial: °F, mph']")
        );

        fButton.click();

        WebElement fTitle = driver.findElement(By.xpath("//span[@class='heading']"));
        String fTitle2 = fTitle.getText();
        String actualResult = fTitle2.substring(fTitle2.length() - 2);

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertTrue(fTitle2.contains(fSymbol));

        driver.quit();
    }


    @Test
    public void testCheckTextInTheBottomOfThePage() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/Users/User/Desktop/Lola/Applications/chromedriver_win32/chromedriver.exe");
        WebDriver driver;
        driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential "
                + "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies"
                + " or manage them individually.";
        String allowAll = "Allow all";
        String manageCookies = " Manage cookies ";

        driver.get(url);
        Thread.sleep(3000);

        WebElement bottomPanel = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__container']")
        );

        WebElement textPanel = driver.findElement(
                By.xpath("//p[@class='stick-footer-panel__description']")
        );

        String actualResultTextPanel = textPanel.getText();

        WebElement allowAllButton = driver.findElement(
                By.xpath("//button[@type='button' and text()='" +  allowAll  + "']")
        );

        WebElement manageButton = driver.findElement(
                By.xpath("//a[@href='/cookies-settings' and text()='" + manageCookies + "']")
        );


        Assert.assertTrue(bottomPanel.isDisplayed());
        Assert.assertTrue(allowAllButton.isDisplayed());
        Assert.assertTrue(manageButton.isDisplayed());
        Assert.assertEquals(actualResultTextPanel, expectedResult);

        driver.quit();

    }

    @Test
    public void testCheckSubMenusExistenceOnThePage() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/Users/User/Desktop/Lola/Applications/chromedriver_win32/chromedriver.exe");
        WebDriver driver;
        driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String supportMenu = "Support";
        String faqSubmenu = "FAQ";
        String howToStartSubmenu = "How to start";
        String askQuestionSubmenu = "Ask a question";
        boolean expectedResult = true;
        String quantityExpectedResult = "3";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement supportMenuElement = driver.findElement(
                By.xpath("//div[@id='support-dropdown' and text()= '" + supportMenu + "']")
        );

        Thread.sleep(2000);
        supportMenuElement.click();
        WebElement faqSubMenuElement = driver.findElement(
                By.xpath("//li[@class='with-dropdown']//a[@href='/faq' and text()='" + faqSubmenu + "']")
        );

        WebElement howToStartSubMenuElement = driver.findElement(
                By.xpath("//li[@class='with-dropdown']//a[@href='/appid' and text()='How to start']")
        );

        WebElement askQuestionSubMenuElement = driver.findElement(
                By.xpath("//li[@class='with-dropdown']//a[text()='Ask a question']")
        );

        boolean actualResultSupport = supportMenuElement.isDisplayed();

        boolean actualResultFAQ = faqSubMenuElement.getText().contains(faqSubmenu);
        boolean actualResultHowToStart = howToStartSubMenuElement.getText().contains(howToStartSubmenu);
        boolean actualResultAskQuestion = askQuestionSubMenuElement.getText().contains(askQuestionSubmenu);

        Assert.assertTrue(actualResultSupport);
        Assert.assertTrue(driver.findElement(By.id("//ul[@id='support-dropdown-menu']")).isDisplayed());
        Assert.assertEquals(driver.findElements(
                By.xpath("//ul[@id='support-dropdown-menu']/*")).size(), quantityExpectedResult);
        Assert.assertTrue(actualResultFAQ);
        Assert.assertTrue(actualResultHowToStart);
        Assert.assertTrue(actualResultAskQuestion);
        Assert.assertEquals(actualResultSupport, expectedResult);
        Assert.assertEquals(actualResultFAQ, expectedResult);
        Assert.assertEquals(actualResultHowToStart, expectedResult);
        Assert.assertEquals(actualResultAskQuestion, expectedResult);

        driver.quit();

    }








}
