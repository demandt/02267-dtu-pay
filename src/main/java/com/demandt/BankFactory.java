package com.demandt;

import dtu.ws.fastmoney.*;

public class BankFactory
{
    private BankService bankService;

    public BankFactory()
    {
        bankService = new BankServiceService().getBankServicePort();
    }

    public BankService getBank()
    {
        return bankService;
    }
}
