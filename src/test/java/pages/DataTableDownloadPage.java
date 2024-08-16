package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import models.Candidate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestExtractor;

public class DataTableDownloadPage extends BasePage {

  public DataTableDownloadPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//div[@id='example_wrapper']//a//span[text()='CSV']/..")
  private WebElement csvFileDownLoadTab;

  @FindBy(xpath = "//div[@id='example_info']")
  private WebElement recordEntriesLbl;

  @FindBy(xpath = "//a[@class='paginate_button next']")
  private WebElement nextClick;

  public void downloadCsvFile() {
    clickElement(csvFileDownLoadTab);
  }

  public List<Candidate> getCandidateFromWebView() {
    waitForElementToBeVisible(csvFileDownLoadTab);
    List<Candidate> candidates = new ArrayList<>();

    // Extract the total number of records
    String text = recordEntriesLbl.getText();
    String regX = "of (\\d+) entries";
    int numberOfRecords = Integer.parseInt(TestExtractor.getExtractedTexr(text, regX));
    int noOfPages = (numberOfRecords + 9) / 10; // Ensure you handle partial pages

    for (int page = 0; page < noOfPages; page++) {
      // Fetch the data rows for the current page
      List<WebElement> dataRows =
          driver.findElements(By.xpath("//table[@id='example']//tbody/tr[@role='row']"));

      for (WebElement dataRow : dataRows) {
        List<WebElement> cells =
            dataRow.findElements(By.xpath("./td")); // Fetch all cells in the current row
        if (cells.size() >= 6) {
          Candidate candidate = new Candidate();
          candidate.setFirstName(cells.get(0).getText());
          candidate.setLastName(cells.get(1).getText());
          candidate.setGender(cells.get(2).getText());
          candidate.setAge(Integer.parseInt(cells.get(3).getText()));
          candidate.setOccupation(cells.get(4).getText());
          candidate.setExperience(Integer.parseInt(cells.get(5).getText()));
          candidates.add(candidate);
        }
      }
      if (isNextButtonClickable()) {
        clickElement(nextClick);
      }
    }
    return candidates;
  }

  private boolean isNextButtonClickable() {
    List<WebElement> nextButtons =
        driver.findElements(By.xpath("//a[@class='paginate_button next']"));
    return nextButtons.size() > 0;
  }

  public void goToWebsite(){
    driver.get("https://www.lambdatest.com/selenium-playground/table-data-download-demo");
  }
}
