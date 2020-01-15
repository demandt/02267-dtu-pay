package com.demandt;

import com.demandt.services.bank.Account;
import com.demandt.services.bank.BankService;
import com.demandt.services.bank.BankServiceException_Exception;
import com.demandt.utils.HelperMethods;

import java.math.BigDecimal;
import java.util.*;

public class DTUPay implements IDTUPay
{
    private BankService bank;
    private HashMap<String, Customer> customers;
    private HashMap<String, Merchant> merchants;
    private List<UUID> authorizedTransactions;

    public List<UUID> getAuthorizedTransactions()
    {
        return authorizedTransactions;
    }

    public DTUPay(BankFactory bankFactory)
    {
        this.bank = bankFactory.getBank();
        customers = new HashMap<>();
        merchants = new HashMap<>();
        authorizedTransactions = new ArrayList<>();
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
                Date date = HelperMethods.getToday();
                Payment customerReceipt = new Payment(token, merchant, givenAmount, date);
                Payment merchantReceipt = new Payment(token, customer, givenAmount, date);
                merchant.getPayments().add(merchantReceipt);
                customer.getPayments().add(customerReceipt);
                UUID transaction = HelperMethods.generateNewToken();
                customer.getReceipts().put(transaction, givenAmount);
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

    public void listCustomerTransactions(Customer customer, Date from, Date to)
    {
        for (Payment payment : customer.getPayments())
        {
            Date transactionTime = payment.getDate();

            if (isDateValid(from, to, transactionTime))
            {
                for (Payment p : customer.getPayments())
                {
                    printCustomerTransaction(p);
                }
            }
        }
    }

    public void listMerchantTransaction(Merchant merchant, Date from, Date to)
    {
        for (Payment payment : merchant.getPayments())
        {
            Date transactionTime = payment.getDate();

            if (isDateValid(from, to, transactionTime))
            {
                for (Payment p : merchant.getPayments())
                {
                    printMerchantTransaction(p);
                }
            }
        }
    }

    private void printMerchantTransaction(Payment payment)
    {
        System.out.println("Transaction information");
        System.out.println("=======================");
        System.out.println("Amount: " + payment.getAmount());
        System.out.println("Token:  " + payment.getToken());
    }

    private void printCustomerTransaction(Payment payment)
    {
        System.out.println("Transaction information");
        System.out.println("=======================");
        System.out.println("Merchant: " + payment.getMerchant().getStoreName());
        System.out.println("Amount:   " + payment.getAmount());
        System.out.println("Token:    " + payment.getToken());
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

    public boolean performRefund(Customer customer, Merchant merchant, UUID transactionID, BigDecimal amount)
    {
        if (checkTransaction(transactionID))
        {
            try
            {
                Account customerAccount = bank.getAccountByCprNumber(customer.getCprNumber());
                Account merchantAccount = bank.getAccountByCprNumber(merchant.getUuid());
                bank.transferMoneyFromTo(merchantAccount.getId(), customerAccount.getId(), amount, "refund");
                UUID transaction = HelperMethods.generateNewToken();
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

    public boolean checkTransaction(UUID transactionID)
    {
        return authorizedTransactions.contains(transactionID);
    }

    public HashMap<String, Customer> getCustomers() { return customers; }


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

    public HashMap<String, Merchant> getMerchants() { return merchants; }
}