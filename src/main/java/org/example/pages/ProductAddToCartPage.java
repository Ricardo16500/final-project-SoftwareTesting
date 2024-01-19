package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductAddToCartPage extends BasePage{
    private By addToCartButton = By.xpath("//button//span[text()='Agregar al Carrito']");
    private By yPosition = By.xpath("//span[text()='Referencia']");

    public ProductAddToCartPage(WebDriver driver) {
        super(driver);
    }
    public void productDisplayed(String productDisplayed){
        driver.findElement(By.xpath("//span[contains(text(),'"+productDisplayed+"')]")).isDisplayed();
    }
    public void scrollToVerticalPosition(WebDriver driver){
        Point location = driver.findElement(yPosition).getLocation();
        int y = location.getY();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0]);",  y);
    }
    public void clickOnAddToCartButton(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(addToCartButton))).click();
    }
}