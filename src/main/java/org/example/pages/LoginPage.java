package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    @Getter
    private By emailLoginButton = By.xpath("//div[contains(@class, 'vtex-login-2-x-button vtex-login-2-x-emailPasswordOptionBtn')]");
    private By userEmail = By.xpath("//input[contains(@placeholder,'ejemplo@mail.com')]");
    private By userPassword = By.xpath("//input[contains(@placeholder,'Ingrese su contraseña')]");

    @Getter
    private By loginBtn = By.xpath("//div[contains(@class, 'vtex-login-2-x-sendButton ml-auto')]");

    private By myAccountText = By.xpath("//button/span[(.='Mi Cuenta')]");
    private By notFindAccount = By.xpath("//div[contains (@class,  'vtex-login-2-x-formError bg-danger--faded t-small tc pa1 mv1 mh0')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnEmailLoginBtn(){
        driver.findElement(emailLoginButton).click();
    }
    public void enterUserEmail(String email){
        driver.findElement(userEmail).sendKeys(email);
    }
    public void enterUserPassword(String password){
        driver.findElement(userPassword).sendKeys(password);
    }
    public void clickOnLoginBtn(){
        driver.findElement(loginBtn).click();
    }

    public boolean isMyAccountDisplayed(){
        return driver.findElement(myAccountText).isDisplayed();
    }
    public boolean isNotFindAccountDisplayed(){
        return driver.findElement(notFindAccount).isDisplayed();
    }
}
