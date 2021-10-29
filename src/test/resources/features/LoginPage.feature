Feature: Login application

  @Login1
  Scenario: Login with valid credentials -Single Test Case
    Given I launch url with chrome browser
    And I enter email address
    And I enter password
    And I click on submit button
    Then I verify Login is success
    And I close the browser


  @Login1
  Scenario Outline: Login with valid credentials - Data Driven Test Case
    Given I launch url with "<browser>" browser
    And I enter email address as "<userName>"
    And I enter password as "<password>"
    And I click on submit button
    Then I verify Login is success
    And I print the <total>
    And I close the browser
    Examples:
      | browser |userName|password|total|
      |chrome   |mani    |mani@123|12   |
      |firefox  |Bhavani |mani@143|14   |
      |chrome   |mani    |mani@123|12   |
      |firefox  |Bhavani |mani@143|14   |
