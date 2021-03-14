package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;

public interface StoreService {

    Store save(Store store);

    Store delete(int id);

    Store delete(Store store);

    Store updateAddress(Address address, int id);

    Store updateName(String name, int id);

    Store[] getAll();

    Store getById(int id);

    Store getByAddress(String name);

    Store getByAddress(Address address);

}
