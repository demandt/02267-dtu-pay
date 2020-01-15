package com.demandt;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {
    String street = "ädd";
    int number = 4;
    int postalCode = 2344;
    String country = "gr";
    private Address address = new Address(street, number, postalCode, country);

    @Test
    public void getStreet() {
        assertEquals(address.getCountry(), country);
    }

    @Test
    public void setStreet() {
        String newStreet = "fwrw";
        address.setStreet(newStreet);
        assertEquals(address.getStreet(), newStreet);
    }

    @Test
    public void getStreetNumber() {
        assertEquals(address.getStreetNumber(), number);
    }

    @Test
    public void setStreetNumber() {
        int newNumber = 4;
        address.setStreetNumber(newNumber);
        assertEquals(address.getStreetNumber(), newNumber);
    }

    @Test
    public void getPostalCode() {
        assertEquals(address.getPostalCode(), postalCode);
    }

    @Test
    public void setPostalCode() {
        int newCode = 23;
        address.setPostalCode(newCode);
        assertEquals(address.getPostalCode(), newCode);
    }

    @Test
    public void getCountry() {
        assertEquals(address.getCountry(), country);
    }

    @Test
    public void setCountry() {
        String newCountry = "fwrw";
        address.setCountry(newCountry);
        assertEquals(address.getCountry(), newCountry);
    }

    @Test
    public void notAddressObject() {
        assertFalse(address.equals("string"));
    }
}