package com.demandt;

import java.math.BigDecimal;
import java.util.UUID;

public interface IDTUPay
{
    boolean registerCustomer(Customer c);
    boolean updateCustomer(Customer c);
    boolean deleteCustomer(Customer c);

    boolean registerMerchant(Merchant m);
    boolean updateMerchant(Merchant m);
    boolean deleteMerchant(Merchant m);

    boolean performPayment(Customer customer, Merchant merchant, UUID token, BigDecimal wantedAmount, BigDecimal givenAmount, String description);
}
