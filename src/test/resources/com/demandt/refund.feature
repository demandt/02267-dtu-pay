Feature: Refund money to customer

  Scenario: A customer applies for a valid refund
    Given a customer with account "Account" applies for a refund of amount 100
    When the customer has a valid receipt of amount 100
    Then the merchant will transfer 100 from the merchants account "Account" to the customers account "Account"

  Scenario: A customer applies for an invalid refund
    Given a customer with account "Account" applies for a refund of amount 100
    When the customer does not have a valid receipt of amount 100
    Then the merchant will deny the refund