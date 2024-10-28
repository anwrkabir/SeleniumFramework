package RSFrameWork.pageObjects;

import RSFrameWork.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage  extends AbstractComponent {

    WebDriver driver;
    public CartPage(   WebDriver driver){
        super(driver); // sending the driver to the parent class
        this.driver  = driver;
        PageFactory.initElements(driver, this); // this represents current class driver

    }

    /**
     * Declaring the page object element  for cartProducts in the CartPage
     * */
    @FindBy (xpath ="//div[@class='cartSection']/h3")
    private List<WebElement> cartProducts;


    /**
     * Declaring the page object element  for checkOut in the CartPage
     * */
    @FindBy (xpath ="//li[@class='totalRow']/button")
    private WebElement checkOutButton;


    /**
     * This is the action method which will validate whether the product name exists on the CartPage or not
     * */

    public  boolean verifyProductDisplay(String productName) {

        Boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
        return match;
    }

    /**
     * This is the action method which will click on the Checkout button on the CartPage
     * Then
     * * */
    public  CheckOutPage goToCheckOut(){
        checkOutButton.click();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }

}
