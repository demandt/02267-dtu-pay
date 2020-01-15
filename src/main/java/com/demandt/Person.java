package com.demandt;

import cucumber.api.java.hu.Ha;

import java.math.BigDecimal;
import java.util.*;

public class Person
{

    private String firstName;
    private String lastName;
    private String cprNumber;
    private Address address;
    private HashMap<UUID, BigDecimal> receipts;

    /* public Person(String firstName, String lastName, String cprNumber, Address address, HashMap<UUID, BigDecimal> receipts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cprNumber = cprNumber;
        this.address = address;
        this.receipts = receipts;
    } */

    public Person(String firstName, String lastName, String cprNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cprNumber = cprNumber;
        this.address = address;
        this.receipts = new LinkedHashMap<>();
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

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setCprNumber(String cprNumber)
    {
        this.cprNumber = cprNumber;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public HashMap<UUID, BigDecimal> getReceipts() {
        return receipts;
    }
}
