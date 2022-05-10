package dungcc.pages;

import dungcc.base.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SigninPage {
    private WebDriver driver;
    private ValidateHelper validateHelper;
    private By emailInput = By.xpath("//input[@id='username']");
    private By passInput = By.xpath("//input[@id='password']");
    private By loginBtn = By.xpath("//span[@class='ng-tns-c215-30 ng-star-inserted']");
     public  SigninPage(WebDriver driver)
     {
         this.driver = driver;
         validateHelper = new ValidateHelper(driver);
     }

    public void singIn(String email , String password)
    {
        validateHelper.setText(emailInput,email);
        validateHelper.setText(passInput,password);
        validateHelper.clickElement(loginBtn);
    }
}
