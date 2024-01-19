package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class PurchasePage extends BasePage{
    private List<By> totalPrice= new ArrayList<>();
    private By title = By.id("orderform-title");
    private By emailDisplayed = By.xpath("//span[(@class='email')]");

    public PurchasePage(WebDriver driver) {
        super(driver);
    }
    public String getTotalPrice(){
        totalPrice.add(By.xpath("//tfoot/tr/td[contains(@class,'monetary')]"));
        return  driver.findElement(totalPrice.get(1)).getText();
    }
    public String getUserEmail(){
        return driver.findElement(emailDisplayed).getText();
    }
    public boolean titleDisplayed(){
        return driver.findElement(title).isDisplayed();
    }
}