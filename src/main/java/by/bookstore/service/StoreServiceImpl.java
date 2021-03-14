package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
import by.bookstore.storage.StoreStorage;
import by.bookstore.storage.inmemory.InMemoryStoreStorage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public class StoreServiceImpl implements StoreService {
    private final StoreStorage storeStorage;

    public StoreServiceImpl(@Qualifier("inMemoryStoreStorage") StoreStorage storeStorage) {
        this.storeStorage = storeStorage;
    }

    @Override
    public Store save(Store store) {
        if (storeStorage.contains(store.getAddress())) {
            return null;
        }
        return storeStorage.add(store);
    }

    @Override
    public Store delete(int id) {
        if (storeStorage.contains(id)) {
            return storeStorage.remove(id);
        }
        return null;
    }

    @Override
    public Store delete(Store store) {
        if (storeStorage.contains(store.getAddress())) {
            return storeStorage.remove(store);
        }
        return null;
    }

    @Override
    public Store updateAddress(Address address, int id) {
        if (storeStorage.contains(id)) {
            return storeStorage.updateAddress(address, id);
        }
        return null;
    }

    @Override
    public Store updateName(String name, int id) {
        if (storeStorage.contains(id)) {
            return storeStorage.updateName(name, id);
        }
        return null;
    }

    @Override
    public Store[] getAll() {
        return storeStorage.getAll();
    }

    @Override
    public Store getById(int id) {
        if (storeStorage.contains(id)) {
            return storeStorage.getById(id);
        }
        return null;
    }

    @Override
    public Store getByAddress(String name) {
        if (storeStorage.contains(name)) {
            return storeStorage.getByName(name);
        }
        return null;
    }

    @Override
    public Store getByAddress(Address address) {
        if (storeStorage.contains(address)) {
            return storeStorage.getByAddress(address);
        }
        return null;
    }
}
