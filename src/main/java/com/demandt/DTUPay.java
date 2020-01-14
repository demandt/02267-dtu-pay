package com.demandt;


import dtu.ws.fastmoney.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DTUPay
{
    private BankService bank;
    private List<Customer> customers;
    private List<Merchant> merchants;
    private List<UUID> authorizedTransactions;

    public DTUPay(BankFactory bankFactory)
    {
        this.bank = bankFactory.getBank();
        customers = new ArrayList<>();
        merchants = new ArrayList<>();
        authorizedTransactions = new ArrayList<>();
    }

    public boolean performPayment(Customer customer, Merchant merchant, UUID token, BigDecimal amount, String description)
    {
        if (merchant.scanToken(token))
        {
            try
            {
                Account customerAccount = bank.getAccountByCprNumber(customer.getCprNumber());
                Account merchantAccount = bank.getAccountByCprNumber(merchant.getUuid());
                bank.transferMoneyFromTo(customerAccount.getId(), merchantAccount.getId(), amount, description);
                return true;
            }
            catch (BankServiceException_Exception e)
            {
                e.getMessage();
                return false;
            }
        }
        return false;
    }

    public List<Customer> getCustomers() { return customers; }

    public List<Merchant> getMerchants() { return merchants; }

    public List<UUID> getAuthorizedTransactions() { return authorizedTransactions; }
}