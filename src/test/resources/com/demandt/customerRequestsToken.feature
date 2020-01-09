Feature: Customer token request

  # Requirement: The customer can request 1 to 5 tokens if he either has spent all tokens (or it is the first time he
  # requests tokens) or has only one unused token left. Overall, a customer can only have at most 6
  # unused tokens.
  Scenario: A customer requests a new set of tokens
    Given the customer has zero tokens
    When  the customer requests a new set of tokens
    Then  the customer is issued 6 new tokens
    Given the customer has 1 token
    When  the customer requests a new set of tokens
    Then  the customer is issued 5 new tokens
  # Requirement: If the user has more than 1 unused token and he requests again a set of tokens, his request will
  # be denied
    Given the customer has more than 1 token
    When  the customer requests a new set of tokens
    Then  the request is denied
