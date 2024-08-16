package pages;

import models.Appointment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoryPage extends BasePage {

  @FindBy(xpath = "//p[@id='facility']")
  private WebElement fecilityNameLbl;

  @FindBy(xpath = "//p[@id='hospital_readmission']")
  private WebElement readmissionLbl;

  @FindBy(xpath = "//p[@id='program']")
  private WebElement programLbl;

  @FindBy(xpath = "//div[@class='panel panel-info']/div[@class='panel-heading']")
  private WebElement visitDate;

  @FindBy(xpath = "//p[@id='comment']")
  private WebElement comments;

  @FindBy(xpath = "//a[@class='btn btn-default']")
  private WebElement goToHomePage;

  @FindBy(xpath = "//a[@id='menu-toggle']")
  private WebElement menuLink;

  @FindBy(xpath = "//a[text()='History']")
  private WebElement historyMenu;

  public HistoryPage(WebDriver driver) {
    super(driver);
  }

  public Appointment getAppointmentDataFromSummaryPage() {
    waitForElementToBeVisible(programLbl);
    clickElement(menuLink);
    clickElement(historyMenu);
    Appointment appointment = new Appointment();
    appointment.setHealthProgram(programLbl.getText());
    appointment.setReadmission(readmissionLbl.getText().equals("Yes"));
    appointment.setComment(comments.getText());
    appointment.setFecilityName(fecilityNameLbl.getText());
    appointment.setVisitDate(visitDate.getText());
    return appointment;
  }

  public void goToHomePage() {
    waitForElementToBeVisible(goToHomePage);
    clickElement(goToHomePage);
  }
}
