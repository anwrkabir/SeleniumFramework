package RahulShettyFrameWork_AppTest.TestComponents;

import RSFrameWork.pageObjects.LandingPage;
import com.aventstack.extentreports.ExtentReports;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
//import java.util.concurrent.TimeUnit;

public class BaseTest {
   public WebDriver driver;
   public  LandingPage landingPage;

    /***
     *
     * This method will get the property set at the 'GlobalData.properties' document to run initialize the expected
     * web driver
     * then maximize the window
     * then defined the implicit wait
     * This method will return the driver
     */

    public  WebDriver initializeDriver() throws IOException
    {
        // using properties class to use the Global Data properties data
        Properties prop  = new Properties();

        // configuring the properties using the system level path
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\RSFrameWork" +
                "\\Resources\\GlobalData\\GlobalData" +
                ".properties");

        // loading the properties
        prop.load(fis);
        // to read any system level variable you need to provide System.getProperty
        String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty(
                "browser");

        //String browserName = prop.getProperty("browser");

/*
        // conditional statement for triggering browser
             if (browserName.contains("chrome")) {

            WebDriverManager.chromedriver().setup();

            if(browserName.contains("headless")){

                ChromeOptions options = new ChromeOptions();

                options.addArguments("--headless=new");
               // options.addArguments("--disable-gpu");
               // options.addArguments("--window-size=1400,800");

                driver = new ChromeDriver(options);

            }


           // driver = new ChromeDriver();
            // if the browser is not headless then still the driver will be run in headed mode.
           // driver = new ChromeDriver();
            //driver.manage().window().setSize(new Dimension(1440,900));// full screen


        }*/

// condition for headless
        if (browserName.contains("headless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=old");
            driver = new ChromeDriver(options);
            //driver.manage().window().setSize(new Dimension(1440,990));
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 990));
        }

        else if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }


        else if(browserName.equals("firefox")){
            System.setProperty("webdriver.gecko.driver","C:\\Tools\\Drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if(browserName.equals("edge"))
        {
            //System.setProperty("webdriver.edge.driver","C:\\Tools\\Drivers\\IEDriverServer.exe");
            //driver = new EdgeDriver();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");
//			options.setCapability("ms:edgeOptions", "{ \"excludeSwitches\": [\"enable-automation\"] }");
            //options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
            Map<String, Object> prefs = new LinkedHashMap<>();
            prefs.put("user_experience_metrics.personalization_data_consent_enabled", Boolean.valueOf(true));
            options.setExperimentalOption("prefs", prefs);
            driver = new EdgeDriver(options);

        }

       //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    /***
     *
     * This method will get the data from json file
     * Arg: filepath of the file
     *  Return: List<HashMap<String, String >>
     *
     */


    public List<HashMap<String, String >> getJasonDataToMap( String filePath) throws IOException {
        //reading json to string
        String jsonContent = FileUtils.readFileToString(new File( filePath), StandardCharsets.UTF_8);

        //String to HashMap ( jackson databind)
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String >> data = mapper.readValue ( jsonContent, new TypeReference
                                        <List <HashMap   <String,String > >  >() { });

              return data;

    }

    /***
     *
     * This method will return where the screenshot file saved
     * Arg: name of the testcase, webdriver
     *  Return: the location where the screenshot saved
     *
     */

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
       File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
       return System.getProperty("user.dir")+"\\reports\\"+ testCaseName +".png";

    }


    /***
     *
     * This method will return the landingPage object
     * This is encapsulating the log in to the application
     * Due to @BeforeMethod annotation, this method will be executed before any test method
     */



    // defining before method to test
    @BeforeMethod(alwaysRun=true)
       public LandingPage  launchApplication() throws IOException {
       driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;

    }

    @AfterMethod(alwaysRun=true)
    public void tearDown(){
       // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.close();
    }
}
