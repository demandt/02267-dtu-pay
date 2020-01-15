package com.demandt.utils;

import com.demandt.Customer;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class HelperMethods
{
    public static boolean isLessThanOrEqualTo(BigDecimal wantedAmount, BigDecimal givenAmount)
    {
        return wantedAmount.compareTo(givenAmount) < 0 || wantedAmount.compareTo(givenAmount) == 0;
    }

    public static Date XMLGregorianCalendarToDate(XMLGregorianCalendar calendar)
    {
        return calendar.toGregorianCalendar().getTime();
    }

    public static Date getToday()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dateFormat.format(date);
        return date;
    }

    public static UUID generateUuid()
    {
        return UUID.randomUUID();
    }

    public static UUID uuidFromString(String uuid) { return UUID.fromString(uuid); }

    public static void printCustomerInformation(Customer customer)
    {
        System.out.println("Customer information");
        System.out.println("====================");
        System.out.println("First name: " + customer.getFirstName());
        System.out.println("Last name:  " + customer.getLastName());
        System.out.println("E-mail:     " + customer.getEmail());
        System.out.println("CPR:        " + customer.getCprNumber());
    }
}
