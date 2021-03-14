package by.bookstore.storage;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;

public interface StoreStorage {

    Store add(Store store);

    Store remove(int id);

    Store remove(Store store);

    Store updateAddress(Address address, int id);

    Store updateName(String name, int id);

    Store[] getAll();

    Store getById(int id);

    Store getByName(String name);

    Store getByAddress(Address address);


    boolean contains(int id);

    boolean contains(Address address);

    boolean contains(String street);


}
