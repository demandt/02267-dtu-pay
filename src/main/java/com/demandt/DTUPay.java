package com.demandt;

import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.Bank;
import dtu.ws.fastmoney.BankServiceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DTUPay
{
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
            catch (BankServiceException e)
            {
                e.getErrorMessage();
                return false;
            }
        }
        return false;
    }

    private Bank bank;
    private List<Customer> customers;
    private List<Merchant> merchants;
    private List<UUID> authorizedTransactions;

    public List<Customer> getCustomers() { return customers; }

    public List<Merchant> getMerchants() { return merchants; }

    public List<UUID> getAuthorizedTransactions() { return authorizedTransactions; }
}