package com.demandt;

import java.math.BigDecimal;
import java.util.UUID;

public interface IManageDTUPay
{
    void updateUser();

    void deleteUser();

    void createUser();

    boolean performPayment(Customer customer, Merchant merchant, UUID token, BigDecimal amount, String description);
}
