package com.demandt;

import dtu.ws.fastmoney.Bank;

public class BankFactory
{
    private Bank instance = null;

    public Bank getInstance()
    {
        return new Bank();
    }
}
