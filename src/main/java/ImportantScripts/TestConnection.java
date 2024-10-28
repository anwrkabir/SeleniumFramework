package ImportantScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TestConnection {


    @Test
    public  void logintoAddingCart() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        List<WebElement> products= driver.findElements(By.xpath("//h4[@class='product-name']"));
        //

        String [] itemNeeded= {"Cucumber","Beetroot","Carrot"};
        int j=0;
        for( int i=0; i< products.size(); i++){


           String[] name =  products.get(i).getText().split(" - ");
           String formattedName = name[0].trim();

           List itemsNeededList = Arrays.asList(itemNeeded);

            System.out.println(formattedName);
           if( itemsNeededList.contains(formattedName)){
               j++;
               driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
               //break; //can not use the break statement in list
                    if(j==products.size()){
                        break;
                    }

           }
        }

        driver.quit();
    }

}
