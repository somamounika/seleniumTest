package pages;

import models.Appointment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SummaryPage extends BasePage {

  @FindBy(xpath = "//p[@id='facility']")
  private WebElement fecilityNameLbl;

  @FindBy(xpath = "//p[@id='hospital_readmission']")
  private WebElement readmissionLbl;

  @FindBy(xpath = "//p[@id='program']")
  private WebElement programLbl;

  @FindBy(xpath = "//p[@id='visit_date']")
  private WebElement visitDate;

  @FindBy(xpath = "//p[@id='comment']")
  private WebElement comments;

  @FindBy(xpath = "//a[@class='btn btn-default']")
  private WebElement goToHomePage;

  public SummaryPage(WebDriver driver) {
    super(driver);
  }

  public Appointment getAppointmentDataFromSummaryPage() {
    waitForElementToBeVisible(programLbl);
    Appointment appointment = new Appointment();
    appointment.setHealthProgram(programLbl.getText());
    appointment.setReadmission(readmissionLbl.getText().equals("Yes"));
    appointment.setComment(comments.getText());
    appointment.setFecilityName(fecilityNameLbl.getText());
    appointment.setVisitDate(visitDate.getText());
    return appointment;
  }

  public void goToHomePage(){
    waitForElementToBeVisible(goToHomePage);
    goToHomePage.click();;
  }
}
