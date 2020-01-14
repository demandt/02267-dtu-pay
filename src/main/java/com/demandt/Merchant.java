package com.demandt;

import java.util.UUID;

public class Merchant extends Store
{
    public Merchant(String name, Address address, String email, String uuid, Person owner)
    {
        super(name, address, email, uuid, owner);
    }

    public boolean scanToken(UUID token)
    {
        return TokenManager.getInstance().approveUseOfToken(token);
    }
}
