package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  @FindBy(xpath = "//a[@id='btn-make-appointment']")
  private WebElement makeAppointment;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public void clickOnMakeAppointment() {
    makeAppointment.click();
  }

  public boolean verifyAppointmentHomePageAvailable() {
    waitForElementToBeVisible(makeAppointment);
    return makeAppointment.isDisplayed();
  }


  public boolean goToWebsite() {
    driver.get("https://katalon-demo-cura.herokuapp.com/");
    return makeAppointment.isDisplayed();
  }
}
