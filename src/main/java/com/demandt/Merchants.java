package com.demandt;

public enum Merchants
{
    COOLSHOP("The Cool Shop");

    private final String name;

    Merchants(String delimiter)
    {
        this.name = delimiter;
    }

    public String getName()
    {
        return name;
    }
}