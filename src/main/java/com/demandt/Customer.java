package com.demandt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Customer extends Person
{
    public Customer(String firstName, String lastName, String cprNumber, Address address)
    {
        super(firstName, lastName, cprNumber, address);
        tokens = new ArrayList<>();
    }

    public Customer(String firstName, String lastName, String cprNumber, Address address, HashMap<UUID, BigDecimal> receipts, ArrayList<UUID> tokens) {
        super(firstName, lastName, cprNumber, address, receipts);
        this.tokens = tokens;
    }

    public boolean applyForRefund(DTUPay dtuPay, Merchant merchant, UUID transactionID) {
        return dtuPay.performRefund(this, merchant, transactionID, this.getReceipts().get(transactionID));
    }

    private ArrayList<UUID> tokens;

    public ArrayList<UUID> getTokens()
    {
        return tokens;
    }
}
