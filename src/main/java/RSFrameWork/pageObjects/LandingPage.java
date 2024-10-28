package RSFrameWork.pageObjects;

import RSFrameWork.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;


    // Initialize driver by constructor
    public LandingPage( WebDriver driver) {
        super(driver); // sending the driver to the parent class
        this.driver  = driver;
        PageFactory.initElements(driver, this); // this represents current class driver
    }


    // only the locator related to the login landing page should be here
    // On the login page, there are 3 locators. so we need to create 3

   //



    //We need to convert the existing locator into pagefactory design structure
    // exisitng locator >> driver.findElement(By.id("userEmail")).sendKeys("akabir@email.com");
    // first "@FindBy ( id ="userEmail")
    // Second  DEFINE WebElement variableName
    // this userEmail will be recognized by the "PageFactory.initElements" which declared in the constructor

    /**
     * Declaring the page object element  for userEmail on the login page
     * */
    @FindBy (id ="userEmail")
    private WebElement userEmail;


    /**
     * Declaring the page object element  for userPassword on the login page
     * */
    @FindBy (id ="userPassword")
    private WebElement userPassword;

    /**
     * Declaring the page object element  for Login button on the login page
     * */

    @FindBy (css ="input[id='login']")
    private WebElement submit;


    /**
     * Declaring the page object element  for Login Error on the login page
     * */

  //  @FindBy (css ="[class*='flyOut']")
    @FindBy (css ="div[id='toast-container']")

    private WebElement errorMessage;






    // following are the action method:
    /***
     *
     * @param email
     * @param password
     * This is the action method which will click on the submit button after parsing email and password during runtime
     * Also this method will return the object of the next page so the control will be
     *
     */

    public   ProductCatalogue loginApplication( String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;

    }

    /***
     *
     * Encapsulating the landing page url
     */
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }


    /***
     *
     * Retrieve the error message
     */
    public String getErrorMessage(){
        waitForElementToVisible(errorMessage);
        return errorMessage.getText();
    }


}
