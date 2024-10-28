package RahulShettyFrameWork_AppTest.TestComponents;

import RSFrameWork.pageObjects.CartPage;
import RSFrameWork.pageObjects.CheckOutPage;
import RSFrameWork.pageObjects.ConfirmationPage;
import RSFrameWork.pageObjects.ProductCatalogue;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends  BaseTest{


    @Test(groups={"purchase"}, retryAnalyzer = Retry.class)
    public  void errorValidationOfSubmitOrder() throws InterruptedException, IOException {

        String productName ="ZARA COAT 3";


        landingPage.loginApplication("akabir@email.com","Rahul" );
        System.out.println( landingPage.getErrorMessage());
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage()) ;
      //"Incorrect email or password."
      //  tearDown();

    }

    @Test
    public  void errorValidationInCartPage() throws InterruptedException, IOException {

        String productName ="ZARA COAT 3";
        ProductCatalogue productCat=  landingPage.loginApplication("mk@email.com","Babla3210" );
        List<WebElement> products= productCat.getProductList();
        productCat.addProductToCart(products, productName);
        productCat.getProducAddedNotification();
        CartPage cartPage= productCat.gotoCartPage(); // calling the parent class method with child class object


        Boolean match =  cartPage.verifyProductDisplay("ZARA COAT 4");
        // validation can not go to the page object files, it must be in the test script
        Assert.assertFalse(match);
       /* CheckOutPage checkOutPage = cartPage.goToCheckOut();
        checkOutPage.selectCountry("China");
        ConfirmationPage confirmation = checkOutPage.submitOrder();



        // get the "Thank you for the order" text after submitting the order
        String  confirmationMessage = confirmation.verifyConfirmationMessage();

        System.out.println(confirmationMessage);
        // Use assert validation to valiate the text after submitting the order
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        tearDown();

*/


    }
}
