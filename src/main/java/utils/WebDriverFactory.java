package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

  private static Properties properties = new Properties();
  private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

  static final String downloadFilepath = "src/main/resources/temp";

  static {
    try {
      properties.load(new FileInputStream("src/main/resources/config.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private WebDriverFactory() {}

  public static WebDriver getDriver() {
    // default chrome if nothing specified
    String browserName =
        properties.get("browser") != null ? properties.get("browser").toString() : "chrome";
    if (driverThreadLocal.get() == null) {
      synchronized (WebDriverFactory.class) {
        if (driverThreadLocal.get() == null) {
          driverThreadLocal.set(createDriver(browserName));
        }
      }
    }
    return driverThreadLocal.get();
  }

  public static WebDriver createDriver(String browserName) {
    WebDriver driver = null;
    boolean isHeadless = Boolean.parseBoolean(properties.get("headless").toString());
    switch (browserName.toLowerCase()) {
      case "chrome":
        System.setProperty(
            "webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.setCapability("acceptInsecureCerts", true);
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-features=PasswordManager,PasswordBreachDetection");
        Map<String, Object> prefs = new HashMap<>();
        String downloadFilepathAbs = new File(downloadFilepath).getAbsolutePath();
        prefs.put("download.default_directory", downloadFilepathAbs);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        if (isHeadless) {
          chromeOptions.addArguments("--headless");
        }
        driver = new ChromeDriver(chromeOptions);
        break;

      case "firefox":
        System.setProperty(
            "webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (isHeadless) {
          firefoxOptions.addArguments("--headless");
        }

        firefoxOptions.addPreference("dom.webnotifications.enabled", false);
        firefoxOptions.addArguments("--width=1920");
        firefoxOptions.addArguments("--height=1080");
        firefoxOptions.setCapability("acceptInsecureCerts", true);
        driver = new FirefoxDriver(firefoxOptions);
        break;

      case "edge":
        System.setProperty(
            "webdriver.edge.driver", properties.getProperty("webdriver.edge.driver"));
        EdgeOptions edgeOptions = new EdgeOptions();
        if (isHeadless) {
          edgeOptions.addArguments("headless");
        }

        edgeOptions.addArguments("start-maximized");
        edgeOptions.addArguments("disable-notifications");
        edgeOptions.setCapability("acceptInsecureCerts", true);
        driver = new EdgeDriver(edgeOptions);
        break;

      default:
        throw new IllegalArgumentException("Unsupported browser: " + browserName);
    }

    return driver;
  }

  public static void quitDriver() {
    if (driverThreadLocal.get() != null) {
      driverThreadLocal.get().quit();
      driverThreadLocal.remove(); // Clean up the ThreadLocal variable
    }
  }
}
