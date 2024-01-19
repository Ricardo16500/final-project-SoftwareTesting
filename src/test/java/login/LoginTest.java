package login;

import config.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.User;
import org.example.pages.LoginPage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import org.example.helpers.ScreenShotHelper;

import org.example.pages.MainPage;

import org.testng.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);


    @Test(description = "loginTest")
    public void loginTest() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        User user = new User();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        log.info("Start login test");
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getLoginButton()));
//        Thread.sleep(4000);
        mainPage.clickLoginButton();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Login correct");
        Assert.assertTrue(loginPage.isMyAccountDisplayed(), "ERROR> no se pudo iniciar sesion");
    }


    @Test(description = "loginTestWithBadPassword")
    public void loginTestWithBadPassword() throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        User user = new User();
        log.info("Start loginTestWithBadPassword");
        mainPage.clickLoginButton();
        log.info("click login button");
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Click login page");
        Thread.sleep(3000);
        loginPage.clickOnEmailLoginBtn();
        log.info("click email button");
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Click email page");
        loginPage.enterUserEmail(user.getEmail());
        log.info("put user email");
        loginPage.enterUserPassword("pROYECTOFINAL2024");
        log.info("put password");
        loginPage.clickOnLoginBtn();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Login incorrect, bad password");
        Assert.assertTrue(loginPage.isNotFindAccountDisplayed(),"ERROR> se pudo iniciar sesion");
    }

    @Test(description = "loginTestWithBadEmail")
    public void loginTestWithBadEmail() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        User user = new User();
        log.info("Start loginTestWithBadEmail");
        mainPage.clickLoginButton();
        log.info("click login button");
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Click login page");
        loginPage.clickOnEmailLoginBtn();
        log.info("click email button");
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Click email page");
        loginPage.enterUserEmail("software@testing.com");
        log.info("put user email");
        loginPage.enterUserPassword(user.getPassword());
        log.info("put password");
        loginPage.clickOnLoginBtn();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Login incorrect, bad email");
        Assert.assertTrue(loginPage.isNotFindAccountDisplayed(),"ERROR> se pudo iniciar sesion");
    }

    @Test(description = "loginTestWithUnregisterUser")
    public void loginTestWithUnregisterUser() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        log.info("Start loginTestWithUnregisterUser");
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Click login page");
        mainPage.clickLoginButton();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Click email page");
        loginPage.clickOnEmailLoginBtn();
        loginPage.enterUserEmail("UCBsoftware@testing.com");
        log.info("put user email");
        loginPage.enterUserPassword("UCBsoftware");
        log.info("put password");
        loginPage.clickOnLoginBtn();
        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Login incorrect unregister user");
        Assert.assertTrue(loginPage.isNotFindAccountDisplayed(),"ERROR> se pudo iniciar sesion");
    }

}

