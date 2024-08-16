package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

  @FindBy(xpath = "//label[@for='demo_account']/../..//*[@placeholder='Username']")
  private WebElement demoUserNamePlaceHolder;

  @FindBy(xpath = "//label[@for='demo_account']/../..//*[@placeholder='Password']")
  private WebElement demoPasswordPlaceHolder;

  @FindBy(xpath = "//input[@id='txt-username']")
  private WebElement userNameTxtBx;

  @FindBy(xpath = "//input[@id='txt-password']")
  private WebElement passwordTxtBx;

  @FindBy(xpath = "//button[@id='btn-login']")
  private WebElement loginBtn;



  public LoginPage(WebDriver driver) {
    super(driver);
  }


  public void doLogin() {
    waitForElementToBeVisible(demoUserNamePlaceHolder);
    waitForElementToBeVisible(demoPasswordPlaceHolder);
    userNameTxtBx.sendKeys(demoUserNamePlaceHolder.getAttribute("value"));
    passwordTxtBx.sendKeys(demoPasswordPlaceHolder.getAttribute("value"));
    clickElement(loginBtn);
  }


}
