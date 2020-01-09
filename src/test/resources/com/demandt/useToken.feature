#Feature: Use a token
#
#  # Requirement: There is a function to use a token. That means, the software is provided with the number/string
#  # from one of the issued tokens and the application should accept the token if the token was no
#  # used before.
#  # Requirement: If the token was already used before, e.g. presented before, then the function should reject the
#  # token.
#  Scenario: Request to use token
#    Given A request to use a token is made
#    When  the token has not been used
#    And   the token is known to the system
#    Then  the usage of that token is granted