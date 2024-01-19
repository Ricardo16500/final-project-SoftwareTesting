package config;

import com.aventstack.extentreports.Status;

import org.example.factory.WebDriverFactory;
import org.example.helpers.ReportManager;
import org.example.helpers.ScreenShotHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    private String url= "https://www.fairplay.com.bo/";

    private static final Logger log = LogManager.getLogger(BaseTest.class);
    @BeforeSuite
    public void setupSuite() throws Exception {
        ReportManager.init("reports", "PurchaseSuite");

    }
    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(ITestResult iTestResult, String browser)throws Exception{

        ReportManager.getInstance().startTest(iTestResult.getMethod().getDescription());
        driver = WebDriverFactory.createWebDriver(browser);

        log.info("Opening browser {}", browser);

        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.fairplay.com.bo/");
      //  driver.get("https://www.fairplay.com.bo/registration?returnUrl=/");   //alternativo enero 10
        log.info("Navigate to {}", url);

        ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.INFO, "Navigate to main page");

    }
    @AfterMethod
    public void tearDown(ITestResult iTestResult) {
        try {
            switch (iTestResult.getStatus()) {
                case ITestResult.FAILURE:
                    log.info("Test failed");
                    ReportManager.getInstance().getTest().log(Status.FAIL, "Test fails");
                    break;
                case ITestResult.SUCCESS:
                    log.info("Test success");
                    ReportManager.getInstance().getTest().log(Status.PASS, "Test passes");
                    break;
                case ITestResult.SKIP:
                    log.info("Test skiped");
                    ReportManager.getInstance().getTest().log(Status.SKIP, "Test skipped");
                    break;
                default:
                    log.info("inclopete test");
                    ReportManager.getInstance().getTest().log(Status.FAIL, "Test incomplete");
            }

            if (iTestResult.getStatus() != ITestResult.SUCCESS && iTestResult.getThrowable() != null) {
                ReportManager.getInstance().getTest().log(Status.FAIL, iTestResult.getThrowable().getMessage());
                ScreenShotHelper.takeScreenShotAndAdToHTMLReport(driver, Status.FAIL, "Failure Image");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //wait for 5 seconds
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (driver != null)
                driver.quit();
            log.info("Closing the driver");
        }
    }

    @AfterSuite
    public static void tearDownSuite() {
        ReportManager.getInstance().flush();
    }




}
