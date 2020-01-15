Feature: Test the Customer

  Scenario: Adding a Customer to the system and then editing his information
    Given a customer been added to the system
    When  the customer had an typing error when added to the system
    Then  the DTUpay would correct his information