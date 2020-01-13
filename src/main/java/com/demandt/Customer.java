package com.demandt;

import java.util.ArrayList;
import java.util.UUID;

public class Customer
{
    public Customer(String firstName, String lastName, String cprNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cprNumber = cprNumber;
        uniqueId = generateGUID();
        tokens = new ArrayList<>();
    }

    private UUID generateGUID()
    {
        return UUID.randomUUID();
    }

    private String firstName;
    private String lastName;
    private String cprNumber;
    private UUID uniqueId;
    private ArrayList<UUID> tokens;

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getCprNumber() { return cprNumber; }

    public UUID getUniqueId() { return uniqueId; }

    public ArrayList<UUID> getTokens()
    {
        return tokens;
    }
}
