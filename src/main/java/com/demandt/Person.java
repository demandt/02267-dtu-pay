package com.demandt;

import cucumber.api.java.hu.Ha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Person
{

    private String firstName;
    private String lastName;
    private String cprNumber;
    private Address address;
    private HashMap<UUID, BigDecimal> receipts;

    public Person(String firstName, String lastName, String cprNumber, Address address, HashMap<UUID, BigDecimal> receipts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cprNumber = cprNumber;
        this.address = address;
        this.receipts = receipts;
    }

    public Person(String firstName, String lastName, String cprNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cprNumber = cprNumber;
        this.address = address;
        this.receipts = new HashMap<UUID, BigDecimal>();
    }

    public Address getAddress()
    {
        return address;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getCprNumber()
    {
        return cprNumber;
    }

    public HashMap<UUID, BigDecimal> getReceipts() {
        return receipts;
    }
}
