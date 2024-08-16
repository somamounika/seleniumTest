package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import models.Candidate;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.DataTableDownloadPage;
import utils.CsvToObjectTransformator;
import utils.FileUtils;

public class DataValidationSteps {

  WebDriver driver;
  DataTableDownloadPage dataTableDownloadPage;

  @Given("user is on website homepage")
  public void user_on_home_page() {
    driver = Hooks.driver;
    dataTableDownloadPage = new DataTableDownloadPage(driver);
    dataTableDownloadPage.goToWebsite();
  }

  @When("user download csv file from website")
  public void down_load_csv() {

    FileUtils.deleteAllFilesInTempDirectory();
    dataTableDownloadPage.downloadCsvFile();
  }

  @Then("validate csv and webview data")
  public void validate_csv_web_view() {
    List<Candidate> candidateListFromWebView = dataTableDownloadPage.getCandidateFromWebView();
    List<Candidate> candidateListFromCsv = CsvToObjectTransformator.getCandidatesFromCSV();

    Assert.assertEquals(
        "size of data should match", candidateListFromWebView.size(), candidateListFromCsv.size());

    for (int i = 0; i < candidateListFromCsv.size(); i++) {
      Assert.assertEquals(
          "firstName should match",
          candidateListFromWebView.get(i).getFirstName(),
          candidateListFromCsv.get(i).getFirstName());
      Assert.assertEquals(
          "LastName should match",
          candidateListFromWebView.get(i).getLastName(),
          candidateListFromCsv.get(i).getLastName());
      Assert.assertEquals(
          "Age should match",
          candidateListFromWebView.get(i).getAge(),
          candidateListFromCsv.get(i).getAge());
      Assert.assertEquals(
          "Occupation should match",
          candidateListFromWebView.get(i).getOccupation(),
          candidateListFromCsv.get(i).getOccupation());
      Assert.assertEquals(
          "Experience should match",
          candidateListFromWebView.get(i).getExperience(),
          candidateListFromCsv.get(i).getExperience());
    }
  }
}
