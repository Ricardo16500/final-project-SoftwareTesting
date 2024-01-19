package addToCart;

import config.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.ProductList;
import org.example.model.User;

import org.example.helpers.ScreenShotHelper;
import org.example.pages.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddToCartTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    @BeforeMethod
    public void loginPage() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
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

    }

    @Test(description = "menProductsAddToCartTest")
    public void menProductsAddToCartTest() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 12);
        ProductList productList = new ProductList();
        MainPage mainPage = new MainPage(driver);
        MenProductsPage menProductsPage = new MenProductsPage(driver);
        ProductAddToCartPage productAddToCartPage = new ProductAddToCartPage(driver);
        CartPage cartPage = new CartPage(driver);
        log.info("Start menProductsAddToCartTest");
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "switch to men products");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getShoppingCartButton()));
        Random random = new Random();
        for (int i= 0; i < productList.menProductsSize(); i++) {
            mainPage.clickManCategory();
            log.info("Switch men products");
            ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "switch to men products");
            wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getShoppingCartButton()));
//            Thread.sleep(7000);
            try {
                menProductsPage.clickOnProduct(productList.getMenProduct(i));
                log.info("Click on product");
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "switch to product");
                productAddToCartPage.scrollToVerticalPosition(driver);
                Thread.sleep(3000);
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "product");
                productAddToCartPage.clickOnAddToCartButton();
                wait.until(ExpectedConditions.visibilityOfElementLocated(cartPage.getCloseButton()));
//            Thread.sleep(3000);
                cartPage.clickOnCloseButton();
            }catch (Exception e){
                log.info("Error: " + e);
            }
        }
        mainPage.clickShoppingCartButton();
        int n = random.nextInt(productList.menProductsSize());
        log.info("Click on shopping cart");
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "products into shopping cart");
        Assert.assertTrue( cartPage.findProductInCartPage(productList.getMenProduct(n)),"ERROR> no se pudo agregar al carrito");
    }
    @Test(description = "cartEmptyTest")
    public void cartEmptyTest() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        MainPage mainPage = new MainPage(driver);
        CartPage cartPage = new CartPage(driver);
        log.info("Start cartEmptyTest");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getShoppingCartButton()));
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "switch to shopping cart");
        mainPage.clickShoppingCartButton();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "shopping cart empty");
        Assert.assertTrue(cartPage.cartEmpty(),"ERROR> el carrtio cuenta con elementos");
    }

    @Test(description = "womenProductsAddToCartTest")
    public void womenProductsAddToCartTest() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ProductList productList = new ProductList();
        log.info("Start womenProductsAddToCartTest");
        MainPage mainPage = new MainPage(driver);
        MenProductsPage menProductsPage = new MenProductsPage(driver);
        ProductAddToCartPage productAddToCartPage = new ProductAddToCartPage(driver);
        CartPage cartPage = new CartPage(driver);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getShoppingCartButton()));
        Random random = new Random();
        for (int i = 0; i < productList.womenProductsSize(); i++) {
            mainPage.clickWomenCategory();
            log.info("Switch men products");
            ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "switch to men products");
            wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getShoppingCartButton()));
            try {
                menProductsPage.clickOnProduct(productList.getWomenProduct(i));
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Women page");
                productAddToCartPage.scrollToVerticalPosition(driver);
                Thread.sleep(3000);
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "add to cart");
                productAddToCartPage.clickOnAddToCartButton();
                Thread.sleep(3000);
                cartPage.clickOnCloseButton();
            }catch (Exception e){
                log.info("Error: " + e);
            }
        }
        mainPage.clickShoppingCartButton();
        int n = random.nextInt(productList.womenProductsSize());
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "products into shopping cart");
        Assert.assertTrue(cartPage.findProductInCartPage(productList.getWomenProduct(n)),
                "ERROR> no se pudo agregar al carrito");
    }
}

