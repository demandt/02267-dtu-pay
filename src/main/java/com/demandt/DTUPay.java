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
    private Utils utils;

    public DTUPay(BankFactory bankFactory)
    {
        this.bank = bankFactory.getBank();
        customers = new ArrayList<>();
        merchants = new ArrayList<>();
        authorizedTransactions = new ArrayList<>();
        this.utils = new Utils();
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
                UUID transaction = utils.generateNewToken();
                customer.getReceipts().put(transaction, amount);
                merchant.getTransactions().add(transaction);
                authorizedTransactions.add(transaction);
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

    public boolean performRefund(Customer customer, Merchant merchant, UUID transactionID, BigDecimal amount) {
        if(checkTransaction(transactionID)) {
            try
            {
                Account customerAccount = bank.getAccountByCprNumber(customer.getCprNumber());
                Account merchantAccount = bank.getAccountByCprNumber(merchant.getUuid());
                bank.transferMoneyFromTo(merchantAccount.getId(), customerAccount.getId(), amount, "refund");
                UUID transaction = utils.generateNewToken();
                customer.getReceipts().remove(transaction);
                merchant.getTransactions().remove(transaction);
                authorizedTransactions.remove(transaction);
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

    public boolean checkTransaction(UUID transactionID) {
        return authorizedTransactions.contains(transactionID);
    }

    public List<Customer> getCustomers() { return customers; }

    public List<Merchant> getMerchants() { return merchants; }

    public List<UUID> getAuthorizedTransactions() { return authorizedTransactions; }
}