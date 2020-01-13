package com.demandt;

import java.util.ArrayList;
import java.util.UUID;

public class Customer
{
    public Customer(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        uniqueId = generateGUID();
        tokens = new ArrayList<>();
    }

    private UUID generateGUID()
    {
        return UUID.randomUUID();
    }

    private String firstName;
    private String lastName;
    private UUID uniqueId;
    private ArrayList<UUID> tokens;

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
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
