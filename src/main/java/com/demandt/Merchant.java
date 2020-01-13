package com.demandt;

import java.util.UUID;

public class Merchant extends Store
{
    private DTUPay dtuPay;

    public Merchant(String storeName, Address address, String email, String uuid, DTUPay dtuPay)
    {
        super(storeName, address, email, uuid);
        this.dtuPay = dtuPay;
    }

    public boolean scanToken(UUID token)
    {
        return TokenManager.getInstance().approveUseOfToken(token);
    }

    public DTUPay getDtuPay() { return dtuPay; }
}
