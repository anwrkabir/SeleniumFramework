package ImportantScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LiveDemo {

    @Test
    public static void main(){
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        // click on table header "Veg/fruit name"
            driver.findElement(By.xpath("//tr/th[1]")).click();
        // capturing only the name of the vegetable/ fruit in the list
            List<WebElement> elementList = driver.findElements(By.xpath("//tr/td[1]"));
        // capture text of all webelements into new (original) list

        List<String> originalList= elementList.stream().map(s->s.getText()).collect(Collectors.toList());
         // sort on the original list of step 3-> sorted list
        List<String> modifiedList =originalList.stream().sorted().collect(Collectors.toList());
        // compare the original list with the sorted list
        Assert.assertTrue(originalList.equals(modifiedList));
        // scan the name column with getText()> serach for "Beans' >> print the price of Beans
        List<String> price = elementList.stream().filter(s-> s.getText().contains("Beans")).
                map(m-> getPriceVeggie(m)).collect(Collectors.toList());
        price.forEach(a-> System.out.println(a));

    }

    private static String getPriceVeggie( WebElement s){
        // getting the price of the respective vegetable
        // here product = //tr/td[1]
        // here price =   //tr/td[1]/following-sibling::td[1]
        // so considering the webelement product, the price will be "following-sibling::td[1]"
        String pricevalue = s.findElement(By.xpath("following-sibling::td[1]")).getText();
        return pricevalue;
    }

}
