package com.demandt;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.*;
import com.demandt.services.bank.*;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

public class StepDefinitions
{
    private BankFactory bankFactory = new BankFactory();
    private DTUPay dtuPay = new DTUPay(bankFactory);
    private TestHelper testHelper = new TestHelper(dtuPay, bankFactory);
    private Customer customer = dtuPay.getCustomers().get(0);
    private Merchant merchant = dtuPay.getMerchants().get(0);
    private UUID transactionID;

    @Given("the customer has zero tokens")
    public void the_customer_has_zero_tokens() {
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
        TokenManager.getInstance().issueToken(customer, 1);
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
        boolean canRequestToken = TokenManager.getInstance().requestToken(customer, tokens);
        assertFalse(canRequestToken);
    }

    // useToken.feature step definitions

    @Given("A request to use a token is made")
    public void a_request_to_use_a_token_is_made()
    {
        TokenManager.getInstance().issueToken(customer, 6);
    }

    @When("the token has not been used")
    public void the_token_has_not_been_used()
    {
        UUID token = customer.getTokens().get(0);
        boolean tokenHasNotBeenUsed = TokenManager.getInstance().checkTokenHasNotBeenUsed(token);
        assertTrue(tokenHasNotBeenUsed);
    }

    @When("the token is known to the system")
    public void the_token_is_known_to_the_system()
    {
        UUID token = customer.getTokens().get(0);
        boolean isTokenValid = TokenManager.getInstance().checkTokenIsValid(token);
        assertTrue(isTokenValid);
    }

    @Then("the usage of that token is granted")
    public void the_usage_of_that_token_is_granted()
    {
        UUID token = customer.getTokens().get(0);
        boolean isTokenApproved = TokenManager.getInstance().approveUseOfToken(token);
        assertTrue(isTokenApproved);
    }

    @Then("the token is used")
    public void the_token_is_used()
    {
        UUID token = customer.getTokens().get(0);
        boolean tokenHasBeenUsed = TokenManager.getInstance().useToken(token);
        assertTrue(tokenHasBeenUsed);
    }

    @Then("checks that the token has been added to the list of used tokens")
    public void checks_that_the_token_has_been_added_to_the_list_of_used_tokens()
    {
        UUID token = customer.getTokens().get(0);
        boolean addedToListOfUsedTokens = TokenManager.getInstance().getUsedTokens().contains(token);
        assertTrue(addedToListOfUsedTokens);
    }

    // payment.Feature

    @Given("the customer has one unused token")
    public void the_customer_has_one_unused_token()
    {
        TokenManager.getInstance().issueToken(customer, 1);
    }

    @When("the merchant scans a token to receive payment")
    public void the_merchant_scans_a_token_to_receive_payment()
    {
        UUID token = customer.getTokens().get(0);
        boolean isTokenValid = merchant.scanToken(token);
        assertTrue(isTokenValid);
    }

    @Then("the payment succeeds")
    public void the_payment_succeeds()
    {
        UUID token = customer.getTokens().get(0);
        BigDecimal amount = new BigDecimal(100);
        transactionID = dtuPay.performPayment(customer, merchant, token, amount, "test");
        assertNotNull(transactionID);
    }

    @And("^the customer get a receipt for an amount of money equal to the payment$")
    public void theCustomerGetAReceiptForAnAmountOfMoneyEqualToThePayment() {
        assertNotNull(customer.getReceipts().get(transactionID));
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
        catch (BankServiceException_Exception e)
        {
            e.getMessage();
        }
    }

    @Then("if the token has already been used or is not known to the system")
    public void if_the_token_has_already_been_used_or_is_not_known_to_the_system()
    {
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
        UUID token = customer.getTokens().get(0);
        BigDecimal amount = new BigDecimal(100);
        transactionID = dtuPay.performPayment(customer, merchant, token, amount, "test");
        assertNotNull(transactionID);
    }

//    @After
//    public void doSomethingAfter() throws BankServiceException_Exception
//    {
//        String customerAccount = bankFactory.getBank().getAccountByCprNumber(customer.getCprNumber()).getId();
//        String merchantAccount = bankFactory.getBank().getAccountByCprNumber(merchant.getUuid()).getId();
//        bankFactory.getBank().retireAccount(customerAccount);
//        bankFactory.getBank().retireAccount(merchantAccount);
//    }


    // ------------------ refund steps ---------------------

    @Given("^a customer with account \"([^\"]*)\" applies for a refund of amount (\\d+)$")
    public void aCustomerWithAccountAppliesForARefundOfAmount(String arg0, int arg1) throws Throwable {
        String cprNumber = testHelper.getBankCustomer().getCprNumber();
        Account account = testHelper.getAccount(cprNumber);
    }

    @When("^the customer has a valid receipt of amount (\\d+)$")
    public void theCustomerHasAValidReceiptOfAmount(int arg0) {

    }

    @Then("^the merchant will transfer (\\d+) from the merchants account \"([^\"]*)\" to the customers account \"([^\"]*)\"$")
    public void theMerchantWillTransferFromTheMerchantsAccountToTheCustomersAccount(int arg0, String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the customer does not have a valid receipt of amount (\\d+)$")
    public void theCustomerDoesNotHaveAValidReceiptOfAmount(int arg0) {
    }

    @Then("^the merchant will deny the refund$")
    public void theMerchantWillDenyTheRefund() {
    }

}
