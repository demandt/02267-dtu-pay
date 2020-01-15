package com.demandt;

import com.demandt.services.bank.Account;
import com.demandt.services.bank.BankService;
import com.demandt.services.bank.BankServiceException_Exception;
import com.demandt.services.bank.Transaction;
import com.demandt.utils.HelperMethods;

import java.math.BigDecimal;
import java.util.*;

public class DTUPay implements IDTUPay
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

    @Override
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

    public void listCustomerTransactions(Customer customer, Date from, Date to)
    {
        try
        {
            Account a = bank.getAccountByCprNumber(customer.getCprNumber());
            List<Transaction> transactionList = a.getTransactions();
            for (Transaction transaction : transactionList)
            {
                Date transactionTime = HelperMethods.XMLGregorianCalendarToDate(transaction.getTime());
                if (isDateValid(from, to, transactionTime))
                {
                    for (Transaction trans : transactionList)
                    {
                        printTransaction(trans);
                    }
                }
            }
        }
        catch (BankServiceException_Exception e)
        {
            e.getMessage();
        }
    }

    private void printTransaction(Transaction trans)
    {
        System.out.println("Transaction information");
        System.out.println("=======================");
        System.out.println("Debtor:       " + trans.getDebtor());
        System.out.println("Creditor:     " + trans.getCreditor());
        System.out.println("Amount:       " + trans.getAmount());
        System.out.println("Time:         " + trans.getTime());
        System.out.println("Description:  " + trans.getDescription());
    }

    private boolean isDateValid(Date from, Date to, Date transactionTime)
    {
        return !transactionTime.before(from) && !transactionTime.after(to);
    }

    public boolean verifyTransaction(BigDecimal wantedAmount, BigDecimal givenAmount)
    {
        return HelperMethods.isLessThanOrEqualTo(wantedAmount, givenAmount);
    }

    @Override
    public boolean registerCustomer(Customer customer)
    {
        if (!hasCustomerBeenRegistered(customer))
        {
            customers.put(customer.getCprNumber(), customer);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer)
    {
        if (hasCustomerBeenRegistered(customer))
        {
            customer.setFirstName(customer.getFirstName());
            customer.setLastName(customer.getLastName());
            customer.setAddress(customer.getAddress());
            customer.setCprNumber(customer.getCprNumber());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(Customer c)
    {
        return false;
    }

    @Override
    public boolean registerMerchant(Merchant merchant)
    {
        if (!hasMerchantBeenRegistered(merchant))
        {
            merchants.put(merchant.getUuid(), merchant);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMerchant(Merchant m)
    {
        return false;
    }

    @Override
    public boolean deleteMerchant(Merchant m)
    {
        return false;
    }

    private boolean hasCustomerBeenRegistered(Customer customer)
    {
        return customers.containsKey(customer.getCprNumber());
    }

    private boolean hasMerchantBeenRegistered(Merchant merchant)
    {
        return merchants.containsKey(merchant.getUuid());
    }

    public HashMap<String, Customer> getCustomers() { return customers; }

    public HashMap<String, Merchant> getMerchants() { return merchants; }
}