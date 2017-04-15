Feature: Demo interacting with an order in Kronos
  As a Member of the Workflow Team
  I want to be able to set key details of an order
  So that it is accurately processed through the pipeline

  Scenario: Change the order origin
    Given tomc has logged into https://staging.kronos.torghub.co.uk
    When I search for an 'Orders' of 'OBE68385'
    Then I can change the 'Order Origin' to a random entry from the list