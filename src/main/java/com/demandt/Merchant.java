package com.demandt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Merchant extends Store
{
    public Merchant(String name, Address address, String email, String uuid, Person owner)
    {
        super(name, address, email, uuid, owner);
        payments = new ArrayList<>();
    }

    private List<Payment> payments;

    public List<Payment> getPayments()
    {
        return payments;
    }

    public boolean scanToken(UUID token)
    {
        return TokenManager.getInstance().approveUseOfToken(token);
    }
}
