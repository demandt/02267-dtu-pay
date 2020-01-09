package com.demandt;

import io.cucumber.java.en.*;

public class StepDefinitions
{
    @Given("the customer has zero tokens")
    public void the_customer_has_zero_tokens()
    {
        Customer customer = new Customer();
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("the customer requests a new set of tokens")
    public void the_customer_requests_a_new_set_of_tokens()
    {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the customer is issued {int} new tokens")
    public void the_customer_is_issued_new_tokens(Integer int1)
    {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("the customer has {int} token")
    public void the_customer_has_token(Integer int1)
    {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("the customer has more than {int} token")
    public void the_customer_has_more_than_token(Integer int1)
    {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the request is denied")
    public void the_request_is_denied()
    {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}
