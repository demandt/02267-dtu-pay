package com.demandt;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoreTest {

    private String storeName = "name";
    private Address address = new Address("place", 2, 32523, "gge");
    private String email = "mail";
    private Person owner = new Person("own", "grgr", "cpr", address);
    private String uuid = "f2534t343rerr";
    Store store = new Store(storeName, address, email, uuid, owner);

    @Test
    public void getUuid() {
        assertEquals(uuid, store.getUuid());
    }

    @Test
    public void getStoreName() {
        assertEquals(storeName, store.getStoreName());
    }

    @Test
    public void getAddress() {
        assertEquals(address, store.getAddress());
    }

    @Test
    public void getEmail() {
        assertEquals(email, store.getEmail());
    }

    @Test
    public void getOwner() {
        assertEquals(owner, store.getOwner());
    }
}