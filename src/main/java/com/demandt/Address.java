package com.demandt;

public class Address
{
    private String street;
    private int streetNumber;
    private int postalCode;
    private String country;

    public Address(String street, int streetNumber, int postalCode, String country)
    {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public int getStreetNumber()
    {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    public int getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(int postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        return address.country.equals(country) && address.postalCode == postalCode
                && address.streetNumber == streetNumber && address.street.equals(street);
    }

//    @Override
//    public int hashCode() {
//        return country.hashCode() + postalCode + streetNumber + street.hashCode();
//    }
}
