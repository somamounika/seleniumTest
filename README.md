# Automated test example in Java with Cucumber and Selenium WebDriver #

## Installation ##

You need to have [Java 8 JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed along with [maven](https://maven.apache.org/download.cgi).

To run the tests locally with Chrome, install ChromeDriver from [here](http://chromedriver.chromium.org) and put at src/main/resources/ Then its location to your system PATH and add webdriver.chrome.driver=src/main/resources/ to your local variables.

To run the tests locally with Firefox, install GeckoDriver from [here](https://github.com/mozilla/geckodriver/releases) and put at src/main/resources/ and add its location to your system PATH.

To install all dependencies, run

```console
$ mvn clean install
```

## Running tests ##

```console
$ mvn test
```

By default, tests will run on Chrome. To change that, specify browser in src/main/resources/config.properties

You can also select specific scenarios to execute using `-Dcucumber.options="--tags @your_tag"`. More info about tags and how to combine them [here](https://github.com/cucumber/cucumber/tree/master/tag-expressions).