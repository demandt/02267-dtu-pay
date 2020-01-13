package com.demandt;

import cucumber.api.java.en.*;
import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankServiceException;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

public class StepDefinitions
{
    private BankFactory bankFactory = new BankFactory();
    private DTUPay dtuPay = new DTUPay(bankFactory);
    private TestHelper testHelper = new TestHelper(dtuPay, bankFactory);

    @Given("the customer has zero tokens")
    public void the_customer_has_zero_tokens()
    {
        
    }

    @When("the customer requests a new set of tokens")
    public void the_customer_requests_a_new_set_of_tokens()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        TokenManager.getInstance().requestToken(customer, 0);
    }

    @Then("the customer is issued 6 new tokens")
    public void the_customer_is_issued_6_new_tokens()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        TokenManager.getInstance().issueToken(customer, 6);
        int actual = customer.getTokens().size();
        int expected = 6;
        assertEquals(actual, expected);
    }

    @Given("the customer has 1 token")
    public void the_customer_has_token()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        TokenManager.getInstance().issueToken(customer, 1);
    }

    @Then("the customer is issued 5 new tokens")
    public void the_customer_is_issued_5_new_tokens()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        TokenManager.getInstance().issueToken(customer, 5);
        int actual = customer.getTokens().size();
        int expected = 6;
        assertEquals(actual, expected);
    }

    @Given("the customer has more than 1 token")
    public void the_customer_has_more_than_1_token()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        TokenManager.getInstance().issueToken(customer, 1);
    }

    @Then("the request is denied")
    public void the_request_is_denied()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        int tokens = customer.getTokens().size();
        boolean canRequestToken = TokenManager.getInstance().requestToken(customer, tokens);
        assertFalse(canRequestToken);
    }

    // useToken.feature step definitions

    @Given("A request to use a token is made")
    public void a_request_to_use_a_token_is_made()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        TokenManager.getInstance().issueToken(customer, 6);
    }

    @When("the token has not been used")
    public void the_token_has_not_been_used()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        UUID token = customer.getTokens().get(0);
        boolean tokenHasNotBeenUsed = TokenManager.getInstance().checkTokenHasNotBeenUsed(token);
        assertTrue(tokenHasNotBeenUsed);
    }

    @When("the token is known to the system")
    public void the_token_is_known_to_the_system()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        UUID token = customer.getTokens().get(0);
        boolean isTokenValid = TokenManager.getInstance().checkTokenIsValid(token);
        assertTrue(isTokenValid);
    }

    @Then("the usage of that token is granted")
    public void the_usage_of_that_token_is_granted()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        UUID token = customer.getTokens().get(0);
        boolean isTokenApproved = TokenManager.getInstance().approveUseOfToken(token);
        assertTrue(isTokenApproved);
    }

    @Then("the token is used")
    public void the_token_is_used()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        UUID token = customer.getTokens().get(0);
        boolean tokenHasBeenUsed = TokenManager.getInstance().useToken(token);
        assertTrue(tokenHasBeenUsed);
    }

    @Then("checks that the token has been added to the list of used tokens")
    public void checks_that_the_token_has_been_added_to_the_list_of_used_tokens()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        UUID token = customer.getTokens().get(0);
        boolean addedToListOfUsedTokens = TokenManager.getInstance().getUsedTokens().contains(token);
        assertTrue(addedToListOfUsedTokens);
    }

    // payment.Feature

    @Given("the customer has one unused token")
    public void the_customer_has_one_unused_token()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        TokenManager.getInstance().issueToken(customer, 1);
    }

    @When("the merchant scans a token to receive payment")
    public void the_merchant_scans_a_token_to_receive_payment()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        UUID token = customer.getTokens().get(0);
        Merchant merchant = dtuPay.getMerchants().get(0);
        boolean isTokenValid = merchant.scanToken(token);
        assertTrue(isTokenValid);
    }

    @Then("the payment succeeds")
    public void the_payment_succeeds()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        Merchant merchant = dtuPay.getMerchants().get(0);
        UUID token = customer.getTokens().get(0);
        BigDecimal amount = new BigDecimal(100);
        boolean isPaymentGranted = dtuPay.performPayment(customer, merchant, token, amount, "test");
        assertTrue(isPaymentGranted);
    }

    @Then("the correct amount is transferred from customer to merchant")
    public void the_correct_amount_is_transferred_from_customer_to_merchant()
    {
        try
        {
            String cprNumber = testHelper.getBankCustomer().getCprNumber();
            Account account = testHelper.getAccount(cprNumber);
            BigDecimal expectedBalance = new BigDecimal(1000 - 100);
            BigDecimal actualBalance = account.getBalance();
            assertEquals(expectedBalance, actualBalance);
        }
        catch (BankServiceException e)
        {
            e.getErrorMessage();
        }
    }

    @Then("if the token has already been used or is not known to the system")
    public void if_the_token_has_already_been_used_or_is_not_known_to_the_system()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        UUID usedToken = customer.getTokens().get(0);

        TokenManager.getInstance().getUsedTokens().add(usedToken);

        boolean isTokenUsed = TokenManager.getInstance().getUsedTokens().contains(usedToken);
        assertTrue(isTokenUsed);

        TokenManager.getInstance().getGeneratedTokens().remove(usedToken);

        UUID fakeToken = UUID.randomUUID();
        boolean isTokenFake = TokenManager.getInstance().getGeneratedTokens().contains(fakeToken);
        assertFalse(isTokenFake);
    }

    @Then("the payment will fail")
    public void the_payment_will_fail()
    {
        Customer customer = dtuPay.getCustomers().get(0);
        Merchant merchant = dtuPay.getMerchants().get(0);
        UUID token = customer.getTokens().get(0);
        BigDecimal amount = new BigDecimal(100);
        boolean isPaymentGranted = dtuPay.performPayment(customer, merchant, token, amount, "test");
        assertFalse(isPaymentGranted);
    }
}
