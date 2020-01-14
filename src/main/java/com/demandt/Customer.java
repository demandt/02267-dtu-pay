package com.demandt;

import java.util.ArrayList;
import java.util.UUID;

public class Customer extends Person
{
    public Customer(String firstName, String lastName, String email, String cprNumber, Address address)
    {
        super(firstName, lastName, cprNumber, address);
        this.email = email;
        tokens = new ArrayList<>();
    }

    private String email;
    private ArrayList<UUID> tokens;

    public String getEmail() { return email; }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public ArrayList<UUID> getTokens()
    {
        return tokens;
    }
}
