package com.demandt.utils;

import java.math.BigDecimal;
import java.util.UUID;

public class HelperMethods
{
    public static boolean isLessThanOrEqualTo(BigDecimal wantedAmount, BigDecimal givenAmount)
    {
        return wantedAmount.compareTo(givenAmount) < 0 || wantedAmount.compareTo(givenAmount) == 0;
    }

    public static String generateUuid()
    {
        return UUID.randomUUID().toString();
    }

    public static UUID uuidFromString(String uuid) { return UUID.fromString(uuid); }
}
