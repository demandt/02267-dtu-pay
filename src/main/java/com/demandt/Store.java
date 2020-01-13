package com.demandt;

public class Store
{
    private String storeName;
    private Address address;
    private String email;
    private String uuid;

    public Store(String storeName, Address address, String email, String uuid)
    {
        this.storeName = storeName;
        this.address = address;
        this.email = email;
        this.uuid = uuid;
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
}
