package steps;

import dataprovider.DataProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Appointment;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.AppointmentPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SummaryPage;
import utils.WebDriverFactory;

public class BookAppointmentSteps {

  WebDriver driver;
  HomePage homePage;
  LoginPage loginPage;
  AppointmentPage appointmentPage;
  SummaryPage summaryPage;
  Appointment appointmentTestData;

  @Given("the user is on the CURA Healthcare Service homepage")
  public void user_on_home_page() {
    driver = Hooks.driver;
    homePage = new HomePage(driver);
    homePage.goToWebsite();
    homePage.verifyAppointmentHomePageAvailable();
    homePage.clickOnMakeAppointment();
  }

  @When(
      "the user books an appointment for a future date {string} {string} {string} {word} {string}")
  public void the_user_books_an_appointment_for_a_future_date(
      String date, String fecility, String healthProgram, String isReadmission, String comment) {
    loginPage = new LoginPage(driver);
    loginPage.doLogin();
    appointmentPage = new AppointmentPage(driver);
    appointmentTestData =
        DataProvider.getAppointmentData(date, fecility, healthProgram, isReadmission, comment);
    appointmentPage.scheduleAppointment(appointmentTestData);
  }

  @Then("the appointment should be listed with correct details in the history")
  public void the_appointment_should_be_listed_with_correct_details_in_the_history() {
    summaryPage = new SummaryPage(driver);
    Appointment actualAppointment = summaryPage.getAppointmentDataFromSummaryPage();
    Assert.assertEquals(
        "fecilityName should match",
        appointmentTestData.getFecilityName(),
        actualAppointment.getFecilityName());
    Assert.assertEquals(
        "HealthProgram should match",
        appointmentTestData.getHealthProgram(),
        actualAppointment.getHealthProgram());
    Assert.assertEquals(
        "VisitDate should match",
        appointmentTestData.getVisitDate(),
        actualAppointment.getVisitDate());
    Assert.assertEquals(
        "readmission should match",
        appointmentTestData.isReadmission(),
        actualAppointment.isReadmission());
    Assert.assertEquals(
        "comment should match", appointmentTestData.getComment(), actualAppointment.getComment());
  }

  @And("the user logs out from the application")
  public void the_user_logs_out_from_the_application() {
    summaryPage.goToHomePage();
    Assert.assertEquals(
        "home page should be visible", homePage.verifyAppointmentHomePageAvailable(), true);
  }
}
