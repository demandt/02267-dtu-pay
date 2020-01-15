package com.demandt;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Payment
{
    private UUID token;
    private Merchant merchant;
    private Customer customer;
    private BigDecimal amount;
    private Date date;

    public Payment(UUID token, Merchant merchant, BigDecimal amount, Date date)
    {
        this.token = token;
        this.merchant = merchant;
        this.amount = amount;
        this.date = date;
    }

    public Payment(UUID token, Customer customer, BigDecimal amount, Date date)
    {
        this.token = token;
        this.customer = customer;
        this.amount = amount;
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }

    public Merchant getMerchant()
    {
        return merchant;
    }

//    public Customer getCustomer()
//    {
//        return customer;
//    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public UUID getToken()
    {
        return token;
    }
}
