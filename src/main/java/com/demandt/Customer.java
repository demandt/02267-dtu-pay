package com.demandt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Customer extends Person
{
    public Customer(String firstName, String lastName, String email, String cprNumber, Address address)
    {
        super(firstName, lastName, cprNumber, address);
        this.email = email;
        tokens = new ArrayList<>();
        payments = new ArrayList<>();
        receipts = new HashMap<>();
    }

    public boolean applyForRefund(DTUPay dtuPay, Merchant merchant, UUID transactionID)
    {
        return dtuPay.performRefund(this, merchant, transactionID, this.getReceipts().get(transactionID));
    }

    private String email;
    private ArrayList<UUID> tokens;
    private List<Payment> payments;
    private HashMap<UUID, BigDecimal> receipts;

    public HashMap<UUID, BigDecimal> getReceipts()
    {
        return receipts;
    }

    public List<Payment> getPayments()
    {
        return payments;
    }

    public String getEmail() { return email; }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public ArrayList<UUID> getTokens()
    {
        return tokens;
    }
}
