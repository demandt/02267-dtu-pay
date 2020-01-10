package com.demandt;

import java.util.ArrayList;
import java.util.UUID;

public class TokenManager
{
    private TokenManager()
    {
        unusedTokens = new ArrayList<>();
        usedTokens = new ArrayList<>();
        generatedTokens = new ArrayList<>();
    }

    public void issueToken(Customer customer, int requestedTokens)
    {
        if (!denyNewTokenRequest(customer, requestedTokens))
        {
            assignTokenToCustomer(customer, requestedTokens);
        }
    }

    private void assignTokenToCustomer(Customer customer, int tokens)
    {
        for (int i = 0; i < tokens; i++)
        {
            UUID token = generateNewToken();
            addToUnusedTokens(token, this.unusedTokens);
            addToGeneratedTokens(token, this.generatedTokens);
            customer.getTokens().add(token);
        }
    }

    public void requestToken(Customer customer)
    {
        if (!denyNewTokenRequest(customer, 0))
        {
            approveNewTokenRequest(customer);
        }
    }

    public boolean denyNewTokenRequest(Customer customer, int tokensRequested)
    {
        return tokenRequestOutOfBounds(customer, tokensRequested);
    }

    private boolean tokenRequestOutOfBounds(Customer customer, int tokensRequested)
    {
        int MAX_ALLOWED_TOKENS = 6;
        return tokensRequested > MAX_ALLOWED_TOKENS ||
                customer.getTokens().size() + tokensRequested > MAX_ALLOWED_TOKENS;
    }

    private boolean approveNewTokenRequest(Customer customer)
    {
        return hasNoTokens(customer) || hasLessThanTwoTokens(customer);
    }

    private boolean hasNoTokens(Customer customer)
    {
        return customer.getTokens().isEmpty();
    }

    private boolean hasLessThanTwoTokens(Customer customer)
    {
        return customer.getTokens().size() < 2;
    }

    private UUID generateNewToken()
    {
        return UUID.randomUUID();
    }

    private void addToUnusedTokens(UUID token, ArrayList<UUID> unusedTokens)
    {
        unusedTokens.add(token);
    }

    private void addToGeneratedTokens(UUID tokens, ArrayList<UUID> generatedTokens)
    {
        generatedTokens.add(tokens);
    }

    public static TokenManager getInstance()
    {
        if (instance == null)
        {
            synchronized (TokenManager.class)
            {
                if (instance == null)
                {
                    instance = new TokenManager();
                }
            }
        }
        return instance;
    }

    private static TokenManager instance = null;
    private ArrayList<UUID> unusedTokens;
    private ArrayList<UUID> usedTokens;
    private ArrayList<UUID> generatedTokens;

    public ArrayList<UUID> getUnusedTokens()
    {
        return unusedTokens;
    }

    public ArrayList<UUID> getUsedTokens()
    {
        return usedTokens;
    }

    public void useToken(UUID token)
    {

    }

    public boolean checkTokenHasNotBeenUsed(UUID token)
    {
        return unusedTokens.contains(token);
    }

    public boolean checkTokenIsValid(UUID token)
    {
        return generatedTokens.contains(token);
    }

    public boolean approveUseOfToken(UUID token)
    {
        return checkTokenHasNotBeenUsed(token) && checkTokenIsValid(token);
    }
}
