package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    @Getter
    private By closeButton = By.xpath("//button[contains(@class, 'vtex-minicart-2-x-closeIconButton')]");

    private By cartEmpty = By.xpath("//p[contains(text(), 'Tu carrito esta vacio')]");

    private By checkoutButton = By.id("proceed-to-checkout");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public void clickOnCloseButton(){
        driver.findElement(closeButton).click();
    }
    public boolean cartEmpty(){
        return driver.findElement(cartEmpty).isDisplayed();
    }
    public void clickOnCheckoutButton(){
        driver.findElement(checkoutButton).click();
    }
    public boolean findProductInCartPage(String product){
        return driver.findElement(By.xpath("//a[(text()='"+product+"')]")).isDisplayed();
    }
}
