package RSFrameWork.AbstractComponents;

import RSFrameWork.pageObjects.CartPage;
import RSFrameWork.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    public AbstractComponent(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this); // this represents current class driver

    }

    /**
     * this method will accept By locator as argument
     * Objective: this method will wait for the By Element to visible
     *
     * */


    public void waitForElementToAppear(By findby) {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
         wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
        }


    /**
     * this method will accept webelemetn as argument
     * Objective: this method will wait for the webelement to be visible
     *
     * */


    public void waitForElementToVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf( webElement));

    }
    /**
     * this method will accept By locator as argument
     * Objective: this method will wait for the By Element to disappear
     *
     * */
    public void waitForElementToDisAppear(By findby) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findby));
    }


    /**
     * Declaring the page object element  for Cart button on the Page Header
     * */

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartHeader;

    /**
     * Declaring the page object element  for ORDERS button on the Page Header
     * */

    @FindBy(css = "button[routerlink='/dashboard/myorders']")
    WebElement orderHeader;


    /**
     * this method is an action method to click on the Cart button on the Page Header that can be accessable
     * This method will also return the object of the next page Cartpage
     *
     * */
    public CartPage gotoCartPage(){
       cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }


    public OrderPage gotoOrdersPage(){
        orderHeader.click();
        OrderPage orderPage= new OrderPage(driver);

        return orderPage;
    }





}
