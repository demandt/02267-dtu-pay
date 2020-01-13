package com.demandt;

import java.util.UUID;

public class Merchant
{
    public Merchant(String name, String cprNumber, DTUPay dtuPay)
    {
        this.name = name;
        this.dtuPay = dtuPay;
        this.cprNumber = cprNumber;
    }

    public boolean scanToken(UUID token)
    {
        return TokenManager.getInstance().approveUseOfToken(token);
    }

    private String name;
    private String cprNumber;
    private DTUPay dtuPay;

    public String getCprNumber() { return cprNumber; }

    public DTUPay getDtuPay() { return dtuPay; }

    public String getName()
    {
        return name;
    }
}
