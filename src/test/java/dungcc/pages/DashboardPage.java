package dungcc.pages;

import dungcc.base.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;
    private ValidateHelper validateHelper;
    private By emailInput = By.xpath("");
    private By passInput = By.xpath("");
    private By loginBtn = By.xpath("");
     public DashboardPage(WebDriver driver)
     {
         this.driver = driver;
         validateHelper = new ValidateHelper(driver);
     }

     public void singIn(String email , String password)
     {
        validateHelper.setText(emailInput,email);
        validateHelper.setText(emailInput,password);
        validateHelper.clickElement(loginBtn);
     }
}
