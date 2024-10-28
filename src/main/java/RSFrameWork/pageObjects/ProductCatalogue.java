package RSFrameWork.pageObjects;

import RSFrameWork.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {

        super(driver); // every child class need to send the driver to its parent class
        this.driver = driver;
        PageFactory.initElements(driver, this); // this represents current class driver

    }

    //  List<WebElement> productList = driver.findElements(By.xpath("//section[@id='products']/div[1]/div[2]/div"));
    //  loc 52 from StandAlone test( need to comment out)
    @FindBy(xpath = "//section[@id='products']/div[1]/div[2]/div")
    List<WebElement> productList;


    /***
     *
     * This action method will retrun the list of the products available on the catalog page
     */
    public List<WebElement> getProductList(){
        return productList;

    }
// to check if the product is present in the catalog page
    /***
     *
     * @param productLists: all the products displaying in the catalog
     * @param productName: name of the expected product in the list of the catalog
     * This method will return the expect product after confirming the expected prdduct out of all avaialable
     *                     product in the list
     *
     */
    public WebElement getProductByName(List<WebElement> productLists, String productName) {

        WebElement expectedproduct =
                productLists.stream().filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        return expectedproduct;
    }


    /***
     *
     * @param productLists: all the products displaying in the catalog
     * @param exproductName: name of the expected product in the list of the catalog
     * This method will click on the "Add to Cart" after confirming the expected prdduct out of all avaialable
     *                     product in the list
     *
     */
    public void addProductToCart( List<WebElement> productLists,String exproductName){
        WebElement prod = getProductByName( productLists,exproductName);
        prod.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();

    }


    // page factor @FindBy annotation is only for driver.findElement.., so for By locator we need to declare
    // 'By Locator' in the class
    // declare 'By productListby' locator to pass to getProductList method


    // By locator "productAddedNotification" for notification "Product Added to Cart"
    By productAddedNotification = By.xpath("//div[@id='toast-container']");

    // By locator "disappearOfSpinningWheel" for spinning wheen after click on the Cart of the expected product
    By disappearOfSpinningWheel = By.cssSelector(".ng-animating");


    /***
     * This method will wait until the display of notification  "Product Added to Cart" and spinning wheel on the
     * page disappear
     */
    public void getProducAddedNotification() {
        waitForElementToAppear(productAddedNotification);
        waitForElementToDisAppear(disappearOfSpinningWheel);
        //return productList;
    }


}
