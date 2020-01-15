package com.demandt;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class CustomerTest {
    private BankFactory bankFactory = new BankFactory();
    private DTUPay dtuPay = new DTUPay(bankFactory);
    private TestHelper testHelper = new TestHelper(dtuPay, bankFactory);
   // private UUID transactionID;
    //private int noOfTransactions = 0;
   // private UUID fakeToken = UUID.randomUUID();
    private Customer customer = dtuPay.getCustomers().get("456789-1234");
    private Merchant merchant = dtuPay.getMerchants().get("456789-3456");

    @Test
    public void getEmail() {
        assertEquals(customer.getEmail(), "test@mail.dk");
    }

    @Test
    public void setEmail() {
        customer.setEmail("newMail@mail.dk");
        assertNotEquals(customer.getEmail(), "test@mail.dk");
        assertEquals(customer.getEmail(), "newMail@mail.dk");
    }
}