Feature: Payment request

  Scenario: The merchant scans a token request to receive payment
    Given the customer has one unused token
    When  the merchant scans a token to receive payment
    Then  the payment succeeds
    Then  the correct amount is transferred from customer to merchant
    But   if the token has already been used or is not known to the system
    Then  the payment will fail
