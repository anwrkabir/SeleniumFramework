package RSFrameWork.pageObjects;

import RSFrameWork.AbstractComponents.AbstractComponent;
//import org.jsoup.internal.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    public ConfirmationPage(   WebDriver driver){
        super(driver); // sending the driver to the parent class
        this.driver  = driver;
        PageFactory.initElements(driver, this); // this represents current class driver

    }


    @FindBy(css ="h1[class='hero-primary']")
    private WebElement confirmationMessageElement;

    public String verifyConfirmationMessage(){
        return confirmationMessageElement.getText();
    }

/*
    // get the "Thank you for the order" text after submitting the order
    String  confirmationMessage = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();

    //    System.out.println(confirmationMessage);
    // Use assert validation to valiate the text after submitting the order
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        driver.close();*/

}
