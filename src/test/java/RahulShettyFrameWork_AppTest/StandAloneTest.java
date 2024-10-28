package RahulShettyFrameWork_AppTest;

import RSFrameWork.pageObjects.*;
import RahulShettyFrameWork_AppTest.TestComponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandAloneTest extends BaseTest {
   String productName ="ZARA COAT 3";


   //declaring global variable for Extent Report
    ExtentReports extent;


    @Test(dataProvider="getData",groups= {"PurchaseOrders"})

    public  void submitOrder( HashMap<String, String> input) throws InterruptedException,
            IOException {

        //Create Entry for Extent Report
        //extent.createTest("Submit Order Test");


        ProductCatalogue productCat=  landingPage.loginApplication(input.get("email"),input.get("password") );
        List<WebElement>  products= productCat.getProductList();
        productCat.addProductToCart(products, input.get("product"));
        productCat.getProducAddedNotification();
        CartPage cartPage= productCat.gotoCartPage(); // calling the parent class method with child class object


        Boolean match =  cartPage.verifyProductDisplay(input.get("product"));
         // validation can not go to the page object files, it must be in the test script
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckOut();
        checkOutPage.selectCountry("China");
        ConfirmationPage confirmation = checkOutPage.submitOrder();



        // get the "Thank you for the order" text after submitting the order
        String  confirmationMessage = confirmation.verifyConfirmationMessage();

        System.out.println(confirmationMessage);
        // Use assert validation to valiate the text after submitting the order
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

       //tearDown();

        // close the Extent Report


       // extent.flush();

    }

    @Test( dependsOnMethods = {"submitOrder"})
    public  void OrderHistoryTest(){

        ProductCatalogue productCat=  landingPage.loginApplication("akabir@email.com","Rahul1212$$" );

        OrderPage  orderPage= productCat.gotoOrdersPage();
        Assert.assertTrue( orderPage.verifyOrderDisplay(productName));
       //"ZARA COAT 3"

        // close the Extent Report
      //  extent.flush();
    }

/*    @DataProvider
    public Object[][] getData(){
        return new Object[][]{{"akabir@email.com", "Rahul1212$$", "ZARA COAT 3"},{"mk@email.com","Bala3210","ADIDAS " +
                "ORIGINAL"}};

    }*/

    @DataProvider
    public Object[][] getData() throws IOException {

     /*   HashMap< String,String> map = new HashMap<String,String>();
        map.put("email","akabir@email.com");
        map.put("password", "Rahul1212$$");
        map.put("product","ZARA COAT 3" );

        HashMap< String,String> map2 = new HashMap<String,String>();
        map2.put("email","mk@email.com");
        map2.put("password", "Babla3210");
        map2.put("product","ADIDAS ORIGINAL" );*/

        List<HashMap<String, String >> data = getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

        return new Object[][]{{data.get(0)},{data.get(1)}};

    }


    /**
     * Extent Report
     * */

    // /*
 /*   @BeforeTest
    public  void  config(){
        String path = System.getProperty("user.dir")+"\\Reports\\TestResult\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter (path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

       // ExtentReports extent = new ExtentReports();
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Md Kabir");

    }*/


}
