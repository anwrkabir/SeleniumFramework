package ImportantScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Base {
    WebDriver driver;



    @Test
public  void logintoAddingCart() throws InterruptedException {

    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);// using implicit waits for every element
    Thread.sleep(3000);
    driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    String [] itemNeeded= {"Cucumber","Beetroot","Carrot"};
    addItems(driver,itemNeeded);
    driver.findElement(By.xpath("//a[@class='cart-icon']")).click();
    driver.findElement( By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
    driver.findElement(By.xpath("//input[@placeholder='Enter promo code']")).sendKeys("rahulshettyacademy");
    driver.findElement(By.xpath("//button[@class='promoBtn']")).click();

    // Explicit wait
      WebDriverWait  w = new WebDriverWait(driver, Duration.ofSeconds(5));
     w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='promoInfo']")));

    System.out.println(driver.findElement(By.xpath("//span[@class='promoInfo']")).getText                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ());

//span[@class='promoInfo']
}

public void addItems( WebDriver driver,String [] itemNeeded){
        List<WebElement> products= driver.findElements(By.xpath("//h4[@class='product-name']"));

        int j=0;
        for( int i=0; i< products.size(); i++){


            String[] name =  products.get(i).getText().split(" - ");
            String formattedName = name[0].trim();

            List itemsNeededList = Arrays.asList(itemNeeded);

            //System.out.println(formattedName);
            if( itemsNeededList.contains(formattedName)){
                j++;
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
                //break; //can not use the break statement in list
                if(j==products.size()){
                    break;
                }

            }
        }
    }

}
