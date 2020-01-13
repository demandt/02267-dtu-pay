package com.demandt;

import dtu.ws.fastmoney.Bank;

public class BankFactory
{
    private static Bank instance = null;

    public static Bank getInstance()
    {
        if (instance == null)
        {
            synchronized (Bank.class)
            {
                if (instance == null)
                {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
