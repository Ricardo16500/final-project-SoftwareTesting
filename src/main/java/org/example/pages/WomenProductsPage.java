package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WomenProductsPage extends BasePage{


    public WomenProductsPage(WebDriver driver) {
        super(driver);
    }
    public void clickOnProduct(String product){
        driver.findElement(By.xpath("//span[contains(text(),'"+product+"')]")).click();
    }
}
