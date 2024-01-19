package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{

    @Getter
    private By loginButton = By.xpath("//div[contains(@class, 'vtex-button__label flex items-center justify-center h-100 ph6')]");
    private By menCategory = By.xpath("//div[contains(text(),'HOMBRE')]");
    private By womenCategory = By.xpath("//div[contains(text(),'MUJER')]");

    @Getter
    private By shoppingCartButton = By.xpath("//span[contains(@class,'vtex-minicart-2-x-minicartIconContainer')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }
    public void clickLoginButton(){driver.findElement(loginButton).click(); }
    public void clickManCategory(){
        driver.findElement(menCategory).click();
    }

    public void clickWomenCategory(){
        driver.findElement(womenCategory).click();
    }

    public void clickShoppingCartButton(){
        driver.findElement(shoppingCartButton).click();
    }
}
