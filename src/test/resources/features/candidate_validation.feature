Feature: Data validation from website and downloaded file

  @DataValidation
  Scenario: Validate Date from CSV downloaded via website view with csv file
    Given  user is on website homepage
    When user download csv file from website
    Then validate csv and webview data
