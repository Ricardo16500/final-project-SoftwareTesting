package confirmPurchase;

import com.aventstack.extentreports.Status;
import config.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.helpers.ScreenShotHelper;
import org.example.model.ProductList;
import org.example.model.User;
import org.example.pages.*;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConfirmPurchaseTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);
    @BeforeMethod
    public void BeforePurchaseTest() throws InterruptedException, IOException {

        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        User user = new User();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        log.info("Start test with login");
        mainPage.clickLoginButton();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Click login page");
        loginPage.clickOnEmailLoginBtn();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Click email page");
        loginPage.enterUserEmail(user.getEmail());
        log.info("put user email");
        loginPage.enterUserPassword(user.getPassword());
        log.info("put password");
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Enter user email and password");
        loginPage.clickOnLoginBtn();
        log.info("click login button");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginPage.getLoginBtn()));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getShoppingCartButton()));

//        Thread.sleep(3000);

        ProductList productList = new ProductList();

        MenProductsPage menProductsPage = new MenProductsPage(driver);
        ProductAddToCartPage productAddToCartPage = new ProductAddToCartPage(driver);
        WomenProductsPage womenProductsPage = new WomenProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        for (int i= 0; i < productList.menProductsSize(); i++) {
            mainPage.clickManCategory();
            ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "men products");
//            Thread.sleep(7000);
            try {
                menProductsPage.clickOnProduct(productList.getMenProduct(i));
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Click product");
                productAddToCartPage.scrollToVerticalPosition(driver);
//            Thread.sleep(3000);
                productAddToCartPage.clickOnAddToCartButton();
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "add to cart");
//            Thread.sleep(3000);
                cartPage.clickOnCloseButton();
            }catch (Exception e){
                System.out.println("no se pudo agregar el producto");
            }
        }
        for (int i = 0; i < productList.menProductsSize(); i++) {
            mainPage.clickWomenCategory();
            ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "women products");
            Thread.sleep(7000);
            try {
                menProductsPage.clickOnProduct(productList.getWomenProduct(i));
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "click product");
                productAddToCartPage.scrollToVerticalPosition(driver);
                Thread.sleep(3000);
                productAddToCartPage.clickOnAddToCartButton();
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "add to cart");
                Thread.sleep(3000);
                cartPage.clickOnCloseButton();
            }catch (Exception e){
                System.out.println("no se pudo agregar el producto");
            }
        }
    }


    @Test(description = "confirmPurchaseTest")
    public void confirmPurchaseTest() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        MainPage mainPage = new MainPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PurchasePage purchasePage = new PurchasePage(driver);


        mainPage.clickShoppingCartButton();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "products into shopping cart");
        Thread.sleep(3000);
        cartPage.clickOnCheckoutButton();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "checkout page");
        String totalPrice = checkoutPage.getTotalPrice();
        System.out.println(totalPrice);
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPage.getFinishButton()));
        checkoutPage.clickOnFinishButton();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "purchase page");
        Assert.assertTrue(purchasePage.titleDisplayed(),"ERROR> no se desplego el titulo");
    }



}
