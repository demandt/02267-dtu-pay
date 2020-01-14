package com.demandt;

import com.demandt.services.bank.Account;
import com.demandt.services.bank.BankService;
import com.demandt.services.bank.BankServiceException_Exception;

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

    public boolean verifyTransaction(BigDecimal wantedAmount, BigDecimal givenAmount)
    {
        return isLessThanOrEqualTo(wantedAmount, givenAmount);
    }

    private boolean isLessThanOrEqualTo(BigDecimal wantedAmount, BigDecimal givenAmount)
    {
        return wantedAmount.compareTo(givenAmount) == -1 || wantedAmount.compareTo(givenAmount) == 0;
    }

    public boolean performPayment(Customer customer, Merchant merchant, UUID token, BigDecimal wantedAmount, BigDecimal givenAmount, String description)
    {
        if (merchant.scanToken(token) && verifyTransaction(wantedAmount, givenAmount))
        {
            try
            {
                Account customerAccount = bank.getAccountByCprNumber(customer.getCprNumber());
                Account merchantAccount = bank.getAccountByCprNumber(merchant.getUuid());
                bank.transferMoneyFromTo(customerAccount.getId(), merchantAccount.getId(), givenAmount, description);
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