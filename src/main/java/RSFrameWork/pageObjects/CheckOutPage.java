package RSFrameWork.pageObjects;

import RSFrameWork.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent {

    WebDriver driver;

    public CheckOutPage(   WebDriver driver){
        super(driver); // sending the driver to the parent class
        this.driver  = driver;
        PageFactory.initElements(driver, this); // this represents current class driver

    }

    @FindBy (xpath ="//input[@placeholder='Select Country']")
    private WebElement Country;

    @FindBy (xpath ="//a[@class='btnn action__submit ng-star-inserted']")
    private WebElement submit;

/*    // selectcountry
        ac.sendKeys(driver.findElement((By.xpath("//input[@placeholder='Select Country']"))),"China").build().perform();*/
    @FindBy (xpath ="(//button[@class='ta-item list-group-item ng-star-inserted'])[1]")
    private WebElement selectCountry;

    /***
     * Declaring the By element result to pass that element in the action method
      */
    By result = By.cssSelector("section[class='ta-results list-group ng-star-inserted']");


    /***
     *

     * @param Countryname
     * This is the action method which will enter the country name,
     * Wait for the autosuggestive countryname to show
     * then from the autosuggestive countryname, select the country and
     * click on the country
     *
     */
    public void selectCountry( String Countryname){
        Actions ac = new Actions(driver);
        ac.sendKeys(Country,Countryname).build().perform(); //"China"
        waitForElementToAppear(result);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();
        ConfirmationPage confirmation =  new ConfirmationPage(driver);
        return confirmation;
    }

    // Country

/*

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // two options with China will pops down

    //css> .ta-results
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class='ta-results list-group ng-star-inserted']")));

    // xpath>> ta-item[2]
        driver.findElement(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[1]")).click();

    // driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")).click();
    //.ta-item:nth-of-type(2) >> means 2nd index

    // click on the submit buttion>> record
        driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

*/




}
