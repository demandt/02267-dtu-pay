package com.demandt;

import java.util.List;
import java.util.UUID;

public class Merchant extends Store
{
    public Merchant(String storeName, Address address, String email, String uuid)
    {
        super(storeName, address, email, uuid);
    }

    public Merchant(String storeName, Address address, String email, String uuid, List<UUID> transactions) {
        super(storeName, address, email, uuid, transactions);
    }

    public boolean scanToken(UUID token)
    {
        return TokenManager.getInstance().approveUseOfToken(token);
    }
}
