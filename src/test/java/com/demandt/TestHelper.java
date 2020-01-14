package com.demandt;

import java.math.BigDecimal;

import com.demandt.services.bank.*;

public class TestHelper
{
    public TestHelper(DTUPay dtuPay, BankFactory bankFactory)
    {
        this.dtuPay = dtuPay;
        this.bank = bankFactory.getBank();
        createUsers();
    }

    private void createUsers()
    {
        Address address = null;
        Customer customer = new Customer("Kristoffer", "Hansen", "456789-1234", address);
        Person shopOwner = new Person("Michael", "Hansen", "456789-2345", address);
        Merchant merchant = new Merchant("TestShop", address, "coolshop@verycoolstuff.com", "456789-3456", shopOwner);

        dtuPay.getCustomers().put(customer.getCprNumber(), customer);
        dtuPay.getMerchants().put(merchant.getUuid(), merchant);

        User bankCustomer = createUser(customer.getFirstName(), customer.getLastName(), customer.getCprNumber());
        User bankMerchant = createUser(shopOwner.getFirstName(), shopOwner.getLastName(), merchant.getUuid());

        BigDecimal initialBalance = new BigDecimal("1000");

        try
        {
            bank.createAccountWithBalance(bankCustomer, initialBalance);
            bank.createAccountWithBalance(bankMerchant, initialBalance);
        }
        catch (BankServiceException_Exception e)
        {
            e.getMessage();
        }
    }

    private User createUser(String firstName, String lastName, String cprNumber)
    {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCprNumber(cprNumber);
        return user;
    }

    private DTUPay dtuPay;
    private BankService bank;
}