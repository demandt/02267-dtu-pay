package com.demandt;

import com.demandt.services.bank.BankService;
import com.demandt.services.bank.BankServiceService;

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
