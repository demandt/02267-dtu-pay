package com.demandt;

import java.util.ArrayList;
import java.util.UUID;

public class Customer extends Person
{
    public Customer(String firstName, String lastName, String cprNumber, Address address)
    {
        super(firstName, lastName, cprNumber, address);
        tokens = new ArrayList<>();
    }
    private ArrayList<UUID> tokens;

    public ArrayList<UUID> getTokens()
    {
        return tokens;
    }
}
