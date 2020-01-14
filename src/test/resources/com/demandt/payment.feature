Feature: Payment request

  # Requirement: When the merchant scans a token request for payment of a specific amount,
  # then the payment succeeds and the correct amount is transferred from the customer to merchant accounts in the bank.
  # Requirement: If the token was already used before, e.g. presented before, then then payment should fail
  # Requirement: If the token is not known to the system (e.g. a fake token), then the again the payment should
  # fail.
  Scenario: The merchant scans a token request to receive payment
    Given the customer has one unused token
    When  the merchant scans a token to receive payment
    Then  the payment succeeds
    And the customer get a receipt for an amount of money equal to the payment
    Then  the correct amount is transferred from customer to merchant
    But   if the token has already been used or is not known to the system
    Then  the payment will fail
