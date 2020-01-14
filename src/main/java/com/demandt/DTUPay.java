package com.demandt;

import com.demandt.services.bank.Account;
import com.demandt.services.bank.BankService;
import com.demandt.services.bank.BankServiceException_Exception;
import com.demandt.utils.HelperMethods;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

public class DTUPay
{
    private BankService bank;
    private HashMap<String, Customer> customers;
    private HashMap<String, Merchant> merchants;

    public DTUPay(BankFactory bankFactory)
    {
        this.bank = bankFactory.getBank();
        customers = new HashMap<>();
        merchants = new HashMap<>();
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

    public boolean verifyTransaction(BigDecimal wantedAmount, BigDecimal givenAmount)
    {
        return HelperMethods.isLessThanOrEqualTo(wantedAmount, givenAmount);
    }

    public HashMap<String, Customer> getCustomers() { return customers; }

    public HashMap<String, Merchant> getMerchants() { return merchants; }
}