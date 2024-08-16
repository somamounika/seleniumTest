package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtils;
import utils.WebDriverFactory;

public class Hooks {

  static public WebDriver driver;

  @Before
  public void startBrowser(Scenario scenario) {
    {
      driver = WebDriverFactory.getDriver();
    }
  }

  @After
  public void closeBrowserAfterScenarioAndTakeScreenshot(Scenario scenario) {
    if (scenario.isFailed()) {
      byte[] screenshot = ScreenshotUtils.captureScreenshot(driver);
      scenario.attach(screenshot, "image/png", scenario.getName() + " is failed");
    }
    WebDriverFactory.quitDriver();
  }
}