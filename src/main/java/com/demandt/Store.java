package com.demandt;

public class Store
{
    private String storeName;
    private Address address;
    private String email;
    private String uuid;
    private Person owner;

    public Store(String storeName, Address address, String email, String uuid, Person owner)
    {
        this.storeName = storeName;
        this.address = address;
        this.email = email;
        this.uuid = uuid; // right now just the CPR-number
        this.owner = owner;
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

    public Person getOwner() { return owner; }
}
