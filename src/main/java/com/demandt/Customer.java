package com.demandt;

import java.util.ArrayList;
import java.util.UUID;

public class Customer
{
    public Customer(String name)
    {
        this.name = name;
        uniqueId = generateGUID();
        tokens = new ArrayList<>();
    }

    private UUID generateGUID()
    {
        return UUID.randomUUID();
    }

    private String name;
    private UUID uniqueId;
    private ArrayList<UUID> tokens;

    public String getName()
    {
        return name;
    }

    public UUID getUniqueId()
    {
        return uniqueId;
    }

    public ArrayList<UUID> getTokens()
    {
        return tokens;
    }
}
