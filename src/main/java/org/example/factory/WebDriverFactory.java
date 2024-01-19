package org.example.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver createWebDriver(String browser) throws Exception {
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "resources/chrome/chromedriver");
                return new ChromeDriver();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "resources/firefox/geckodriver");
                return new FirefoxDriver();
            default:
                throw new Exception("El navegador " + browser + " no es soportado");
        }
    }
}
