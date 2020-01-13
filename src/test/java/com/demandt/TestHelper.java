package com.demandt;

import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.Bank;
import dtu.ws.fastmoney.BankServiceException;
import dtu.ws.fastmoney.User;

import java.math.BigDecimal;

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
        Customer customer = new Customer("Customer", "Customersen", "456789-1234", address);
        Merchant merchant = new Merchant("Coolshop", address, "coolshop@verycoolstuff.com", "456789-2345");

        dtuPay.getCustomers().add(customer);
        dtuPay.getMerchants().add(merchant);

        bankCustomer = createUser(customer.getFirstName(), customer.getLastName(), customer.getCprNumber());
        bankMerchant = createUser("Merchant", "Merchantsen", "456789-2345");

        BigDecimal initialBalance = new BigDecimal("1000");

        try
        {
            bank.createAccountWithBalance(bankCustomer, initialBalance);
            bank.createAccountWithBalance(bankMerchant, initialBalance);
        }
        catch (BankServiceException e)
        {
            e.getErrorMessage();
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
    private Bank bank;
    private User bankCustomer;
    private User bankMerchant;

    public DTUPay getDtuPay() { return dtuPay; }

    public User getBankCustomer() { return bankCustomer; }

    public User getBankMerchant() { return bankMerchant; }

    public Account getAccount(String cprNumber) throws BankServiceException { return bank.getAccount(cprNumber); }
}