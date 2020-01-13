package com.demandt;

import dtu.ws.fastmoney.Bank;

public class BankFactory
{
    private Bank bank;

    public BankFactory() { bank = new Bank(); }

    public Bank getBank() { return bank; }
}
