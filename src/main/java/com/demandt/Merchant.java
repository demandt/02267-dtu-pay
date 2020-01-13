package com.demandt;

import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankServiceException;

import java.math.BigDecimal;
import java.util.UUID;

public class Merchant
{
    public Merchant(String name, String cprNumber, DTUPay dtuPay)
    {
        this.name = name;
        this.dtuPay = dtuPay;
        this.cprNumber = cprNumber;
    }

    private String cprNumber;

    public String getCprNumber()
    {
        return cprNumber;
    }

    public boolean scanToken(UUID token)
    {
        return TokenManager.getInstance().approveUseOfToken(token);
    }

    public boolean payAtMerchant(Customer customer, UUID token, BigDecimal amount, String description)
    {
        if (scanToken(token))
        {
            try
            {
                Account customerAccount = BankFactory.getInstance().getAccountByCprNumber(customer.getCprNumber());
                Account merchantAccount = BankFactory.getInstance().getAccountByCprNumber(this.cprNumber);
                BankFactory.getInstance().transferMoneyFromTo(customerAccount.getId(), merchantAccount.getId(), amount, description);
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

    private DTUPay dtuPay;

    public DTUPay getDtuPay()
    {
        return dtuPay;
    }

    private String name;

    public String getName()
    {
        return name;
    }
}
