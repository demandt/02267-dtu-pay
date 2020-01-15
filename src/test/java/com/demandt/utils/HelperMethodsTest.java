package com.demandt.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

public class HelperMethodsTest {

    @Test
    public void isLessThanOrEqualTo() {
        assertTrue(HelperMethods.isLessThanOrEqualTo(BigDecimal.valueOf(0), BigDecimal.valueOf(10)));
    }

    @Test
    public void generateNewToken() {
        UUID token = HelperMethods.generateNewToken();
        assertNotEquals(token, UUID.randomUUID());
    }
}