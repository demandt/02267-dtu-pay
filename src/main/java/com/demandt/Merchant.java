package com.demandt;

import java.util.UUID;

public class Merchant extends Store
{
    public Merchant(String storeName, Address address, String email, String uuid)
    {
        super(storeName, address, email, uuid);
    }

    public boolean scanToken(UUID token)
    {
        return TokenManager.getInstance().approveUseOfToken(token);
    }
}
