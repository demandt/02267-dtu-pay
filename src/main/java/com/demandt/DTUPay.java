package com.demandt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DTUPay
{
    public DTUPay()
    {
        customers = new ArrayList<>();
        merchants = new ArrayList<>();
        authorizedTransactions = new ArrayList<>();
    }

    private List<Customer> customers;
    private List<Merchant> merchants;
    private List<UUID> authorizedTransactions;

    public List<Customer> getCustomers()
    {
        return customers;
    }

    public List<Merchant> getMerchants()
    {
        return merchants;
    }

    public List<UUID> getAuthorizedTransactions()
    {
        return authorizedTransactions;
    }
}