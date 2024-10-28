package RahulShettyFrameWork_AppTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
//import java.util.concurrent.TimeUnit;

public class OriginalStandAloneTest {


    WebDriver driver;

    @Test
    public  void testDashboard() throws InterruptedException {

        String productName ="ZARA COAT 3";
       // WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
       // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("akabir@email.com");
        driver.findElement(By.id("userPassword")).sendKeys("Rahul1212$$");
        driver.findElement(By.cssSelector("input[id='login']")).click();

        List<WebElement> productList = driver.findElements(By.xpath("//section[@id='products']/div[1]/div[2]/div"));
	     /*   ListIterator <WebElement> plt = productList.listIterator();
	        WebElement addToCart = driver.findElement(By.xpath("//button[@class='btn w-10 rounded']"));
	*/
	/*
	        // looping the list to find the product name Zara by using while loop
	        Thread.sleep(5000);
	        while (plt.hasNext()){
	            WebElement pt = plt.next();
	            System.out.println(pt.getText());
	            if(pt.getText().contains("ZARA")){
	                addToCart.click();
	            }
	        }
	*/

	/*      WebElement prod = productList.stream().filter(s-> s.getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
	        prod.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
	*/
        // to return all test from the section
        // productList.stream().map(s->s.getText()).forEach(System.out::println);

        WebElement prod =
                productList.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        // click on "Add to Cart" button
        prod.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();

        // Creating explicit wait for the "Product Added to Cart" banner
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // WebElement orderStatus = driver.findElement(By.xpath("//div[@id='toast-container']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
        // creating explicit wait for the disappear of the spinning wheel
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        // driver.findElement(By.cssSelector().c

        // Cick on the "Cart" button to see the item added in the cart

        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();


        // text value of the products in Cart
        List<WebElement> cartProducts =
                driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        // validate if the product "ZARA COAT 3" is present in the Cart, we can do it either using Filter() or using
        // anyMatch()
        // cartProducts.stream().filter(s-> s.getText().equals(productName));
        Boolean match=  cartProducts.stream().anyMatch(s-> s.getText().equalsIgnoreCase(productName));
        // add assertion for the existance of the product name in the cart
        Assert.assertTrue(match);

        // click on the "Checkout" button
        driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();

        // click on the "Select Country" auto suggestive drop down on the "Place Order" page
        // creating action object to handle that scenario
        Actions ac = new Actions(driver);
        ac.sendKeys(driver.findElement((By.xpath("//input[@placeholder='Select Country']"))),"China").build().perform();
        // two options with China will pops down
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class='ta-results list-group ng-star-inserted']")));
        driver.findElement(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[1]")).click();

        // driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")).click();
        //.ta-item:nth-of-type(2) >> means 2nd index

        // click on the submit buttion
        driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

        // get the "Thank you for the order" text after submitting the order
        String  confirmationMessage = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();

        System.out.println(confirmationMessage);
        // Use assert validation to valiate the text after submitting the order
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        driver.close();

    }

}
