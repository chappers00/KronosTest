Feature: Demo interacting with an order in Kronos
  As a Member of the Workflow Team
  I want to have an accurate summary of the order
  So that I am confident I have correct information

  Scenario: Verify details on order summary page
    Given tomc has logged into staging.kronos.torghub.co.uk
    When I search for an 'Orders' of 'OBE68385'
    Then the customer's details should be:
      |name                 |email            |telephone  |
      |Rhodri James Walters |p560653@mvrht.com|02073919051|
    And the following fields should be set in the order summary:
      |Level      |Undergraduate        |
      |Type       |Essay                |
      |Grade      |Standard 2:1 (60-64%)|
      |Word Count |1000                 |