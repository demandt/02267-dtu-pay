package com.demandt;

import java.util.ArrayList;
import java.util.UUID;

public class Customer extends Person
{
    public Customer(String firstName, String lastName, String cprNumber, Address address)
    {
        super(firstName, lastName, cprNumber, address);
        uniqueId = generateGUID();
        tokens = new ArrayList<>();
    }

    private UUID generateGUID()
    {
        return UUID.randomUUID();
    }

    private UUID uniqueId;
    private ArrayList<UUID> tokens;

    public UUID getUniqueId() { return uniqueId; }

    public ArrayList<UUID> getTokens()
    {
        return tokens;
    }
}
