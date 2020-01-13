package com.demandt;

import dtu.ws.fastmoney.BankServiceException;
import dtu.ws.fastmoney.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DTUPay
{
    public DTUPay()
    {
        createUsers();
        authorizedTransactions = new ArrayList<>(); // DTUPay should record tokens used to authorize payments
    }

    private void createUsers()
    {
        customers = new ArrayList<>();
        merchants = new ArrayList<>();

        Customer customer = new Customer("Customer", "Customersen");
        Merchant merchant = new Merchant(Merchants.COOLSHOP, this);

        customers.add(customer);
        merchants.add(merchant);

        bankCustomer = createUser(customer.getFirstName(), customer.getLastName(), "456789-1234");
        bankMerchant = createUser("Merchant", "Merchantsen", "456789-2345");

        BigDecimal initialBalance = new BigDecimal("1000");

        try
        {
            BankFactory.getInstance().createAccountWithBalance(bankCustomer, initialBalance);
            BankFactory.getInstance().createAccountWithBalance(bankMerchant, initialBalance);
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

    private List<Customer> customers;

    public List<Customer> getCustomers()
    {
        return customers;
    }

    private List<Merchant> merchants;

    public List<Merchant> getMerchants()
    {
        return merchants;
    }

    private User bankCustomer;

    public User getBankCustomer()
    {
        return bankCustomer;
    }

    private User bankMerchant;

    public User getBankMerchant()
    {
        return bankMerchant;
    }

    private List<UUID> authorizedTransactions;

    public List<UUID> getAuthorizedTransactions()
    {
        return authorizedTransactions;
    }
}