package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{

    @Getter
    private By finishButton = By.xpath("//a[contains(text(),'Finalizar compra')]");
    private By getTotalPrice = By.xpath("//tfoot/tr/td[contains(@class,'monetary')]");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public void findProductInCartPage(String product){
        driver.findElement(By.xpath("//td/a[contains(text(),'"+product+"')]")).isDisplayed();
    }
    public void clickOnFinishButton(){
        driver.findElement(finishButton).click();
    }
    public String getTotalPrice(){
        return driver.findElement(getTotalPrice).getText();
    }
}