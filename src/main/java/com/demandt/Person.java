package com.demandt;

public class Person
{
    public Person(String firstName, String lastName, String cprNumber, Address address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cprNumber = cprNumber;
        this.address = address;
    }

    private String firstName;
    private String lastName;
    private String cprNumber;
    private Address address;

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
}
