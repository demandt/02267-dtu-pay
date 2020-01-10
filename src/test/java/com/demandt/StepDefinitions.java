package com.demandt;

import io.cucumber.java.en.*;

import java.util.UUID;

import static org.junit.Assert.*;

public class StepDefinitions
{
    private Customer customer;

    @Given("the customer has zero tokens")
    public void the_customer_has_zero_tokens()
    {
        customer = new Customer("TestCustomer");
    }

    @When("the customer requests a new set of tokens")
    public void the_customer_requests_a_new_set_of_tokens()
    {
        TokenManager.getInstance().requestToken(customer, 0);
    }

    @Then("the customer is issued 6 new tokens")
    public void the_customer_is_issued_6_new_tokens()
    {
        TokenManager.getInstance().issueToken(customer, 6);
        int actual = customer.getTokens().size();
        int expected = 6;
        assertEquals(actual, expected);
    }

    @Given("the customer has 1 token")
    public void the_customer_has_token()
    {
        customer.getTokens().subList(1, 6).clear();
    }

    @Then("the customer is issued 5 new tokens")
    public void the_customer_is_issued_5_new_tokens()
    {
        TokenManager.getInstance().issueToken(customer, 5);
        int actual = customer.getTokens().size();
        int expected = 6;
        assertEquals(actual, expected);
    }

    @Given("the customer has more than 1 token")
    public void the_customer_has_more_than_1_token()
    {
        TokenManager.getInstance().issueToken(customer, 1);
    }

    @Then("the request is denied")
    public void the_request_is_denied()
    {
        int tokens = customer.getTokens().size();
        assertFalse(TokenManager.getInstance().requestToken(customer, tokens));
    }

    // useToken.feature step definitions

    @Given("A request to use a token is made")
    public void a_request_to_use_a_token_is_made()
    {
        customer = new Customer("TestCustomer");
        TokenManager.getInstance().issueToken(customer, 6);
    }

    @When("the token has not been used")
    public void the_token_has_not_been_used()
    {
        UUID token = customer.getTokens().get(0);
        assertTrue(TokenManager.getInstance().checkTokenHasNotBeenUsed(token));
    }

    @When("the token is known to the system")
    public void the_token_is_known_to_the_system()
    {
        UUID token = customer.getTokens().get(0);
        assertTrue(TokenManager.getInstance().checkTokenIsValid(token));
    }

    @Then("the usage of that token is granted")
    public void the_usage_of_that_token_is_granted()
    {
        UUID token = customer.getTokens().get(0);
        assertTrue(TokenManager.getInstance().approveUseOfToken(token));
    }

    @Then("the token is used")
    public void the_token_is_used()
    {
        UUID token = customer.getTokens().get(0);
        TokenManager.getInstance().useToken(token);
    }

    @Then("checks that the token has been added to the list of used tokens")
    public void checks_that_the_token_has_been_added_to_the_list_of_used_tokens()
    {
        UUID token = customer.getTokens().get(0);
        assertTrue(TokenManager.getInstance().getUsedTokens().contains(token));
    }
}
