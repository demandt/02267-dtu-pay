package com.demandt;

public class Person
{

    private String firstName;
    private String lastName;
    private String cprNumber;
    private Address address;

    public Person(String firstName, String lastName, String cprNumber, Address address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cprNumber = cprNumber;
        this.address = address;
    }
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getCprNumber()     {
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

}
