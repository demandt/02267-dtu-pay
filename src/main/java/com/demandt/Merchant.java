package com.demandt;

import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankServiceException;

import java.math.BigDecimal;
import java.util.UUID;

public class Merchant
{
    public Merchant(String name, DTUPay dtuPay)
    {
        this.name = name;
        this.dtuPay = dtuPay;
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
                String customerCpr = dtuPay.getBankCustomer().getCprNumber();
                Account customerAccount = BankFactory.getInstance().getAccountByCprNumber(customerCpr);
                String merchantCpr = dtuPay.getBankMerchant().getCprNumber();
                Account merchantAccount = BankFactory.getInstance().getAccountByCprNumber(merchantCpr);
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
