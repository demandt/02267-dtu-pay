package com.demandt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Store
{
    private String storeName;
    private Address address;
    private String email;
    private String uuid;
    private List<UUID> transactions;


    public Store(String storeName, Address address, String email, String uuid, List<UUID> transactions) {
        this.storeName = storeName;
        this.address = address;
        this.email = email;
        this.uuid = uuid;
        this.transactions = transactions;
    }

    public Store(String storeName, Address address, String email, String uuid)
    {
        this.storeName = storeName;
        this.address = address;
        this.email = email;
        this.uuid = uuid; // right now just the CPR-number
        this.transactions = new ArrayList<UUID>();
    }

    public String getUuid()
    {
        return uuid;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public Address getAddress()
    {
        return address;
    }

    public String getEmail()
    {
        return email;
    }

    public List<UUID> getTransactions() {
        return transactions;
    }
}
