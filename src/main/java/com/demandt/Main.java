package com.demandt;

import com.demandt.utils.HelperMethods;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello, World");
        Address address = null;
        Customer customer = new Customer("Kristoffer", "Hansen", "test@maik.dk", "456789-1234", address);
        HelperMethods.printCustomerInformation(customer);
        customer.setLastName("Demandt");
        customer.setEmail("new@mail.dk");
        HelperMethods.printCustomerInformation(customer);
    }

    public static void updateCustomer(Customer customer)
    {
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setAddress(customer.getAddress());
        customer.setCprNumber(customer.getCprNumber());
    }
}
