package pages;

import java.util.List;
import models.Appointment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppointmentPage extends BasePage {

  @FindBy(xpath = "//select[@name='facility']")
  private WebElement fecilitySelectBx;

  @FindBy(xpath = "//select[@name='facility']//option")
  private List<WebElement> fecilityOptions;

  @FindBy(xpath = "//input[@name='hospital_readmission']")
  private WebElement readmissionCheckbx;

  @FindBy(xpath = "//label[@class='radio-inline']/input")
  private List<WebElement> healthCareProgramsRadio;

  @FindBy(xpath = "//input[@name='visit_date']")
  private WebElement visitDate;

  @FindBy(xpath = "//textarea[@name='comment']")
  private WebElement commentsTxtArea;

  @FindBy(xpath = "//button[@id='btn-book-appointment']")
  private WebElement bookAppointmentBtn;

  public AppointmentPage(WebDriver driver) {
    super(driver);
  }

  public void scheduleAppointment(Appointment appointment) {
    waitForElementToBeVisible(fecilitySelectBx);
    WebElement selectedFecility = fecilityOptions.stream()
        .filter(ele -> ele.getText().equalsIgnoreCase(appointment.getFecilityName())).findFirst()
        .orElseThrow();
    selectedFecility.click();
    waitForElementToBeVisible(readmissionCheckbx);
    if (appointment.isReadmission()) {
      readmissionCheckbx.click();
    }
    WebElement selectedHealthCareProgram = healthCareProgramsRadio.stream()
        .filter(ele -> ele.getAttribute("value").contains(appointment.getHealthProgram()))
        .findFirst()
        .orElseThrow();
    selectedHealthCareProgram.click();
    visitDate.sendKeys(appointment.getVisitDate());
    commentsTxtArea.sendKeys(appointment.getComment());
    bookAppointmentBtn.click();
  }
}
