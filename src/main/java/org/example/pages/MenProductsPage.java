package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenProductsPage extends BasePage{
    // //span[contains(text(),'PUMA GORRA FERRARI SPTWR STYLE BB')]  para localizar los diversos productos de la categoria hombre

    public MenProductsPage(WebDriver driver) {
        super(driver);
    }
    public void clickOnProduct(String product){
        driver.findElement(By.xpath("//span[contains(text(),'"+product+"')]")).click();
    }

}
