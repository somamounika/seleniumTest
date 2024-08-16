package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtils;
import utils.WebDriverFactory;

public class Hooks {

  public static WebDriver driver;

  @Before
  public void startBrowser(Scenario scenario) {
    driver = WebDriverFactory.getDriver();
  }

  @AfterStep
  public void takeScreenshotAfterStep(Scenario scenario) {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    String screenshotName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";
    String screenshotDir = "target/screenshots/";
    File directory = new File(screenshotDir);
    if (!directory.exists()) {
      directory.mkdirs();
    }
    File destinationPath = new File(screenshotDir + screenshotName);
    try {
      FileUtils.copyFile(screenshot, destinationPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @After
  public void closeBrowserAfterScenarioAndTakeScreenshot(Scenario scenario) {
    byte[] screenshot = ScreenshotUtils.captureScreenshot(driver);
    scenario.attach(screenshot, "image/png", scenario.getName());
    WebDriverFactory.quitDriver();
  }
}
