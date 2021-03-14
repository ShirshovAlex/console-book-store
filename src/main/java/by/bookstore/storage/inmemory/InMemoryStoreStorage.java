package by.bookstore.storage.inmemory;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
import by.bookstore.storage.StoreStorage;
import org.springframework.stereotype.Component;


public class InMemoryStoreStorage implements StoreStorage {
    private static final Store[] stores = new Store[50];
    private int size = 0;

    static {
        stores[0] = new Store("malina", new Address("malina30"));
        stores[1] = new Store("truda", new Address("truda25"));
    }

    @Override
    public Store add(Store store) {
        if (stores.length == size) return null;
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) {
                stores[i] = store;
                size++;
                return store;
            }
        }
        return null;
    }

    private void checkId(int id) {
        if (size == 0 || id < 0) {
            throw new IllegalStateException();
        }
    }

    @Override
    public Store remove(int id) {
        checkId(id);
        Store old;
        for (int i = 0; i < stores.length; i++) {
            if (stores[i].getId() == id) {
                old = stores[i];
                for (int j = 0; j < stores.length - 1; j++) {
                    stores[j] = stores[j + 1];
                }
                size--;
                return old;
            }
        }
        return null;
    }

    private void checkStore(Store store) {
        if (store == null || size == 0) {
            throw new NullPointerException();
        }
    }

    @Override
    public Store remove(Store store) {
        checkStore(store);
        Store old;
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].equals(store)) {
                old = stores[i];
                for (int j = 0; j < stores.length - 1; j++) {
                    stores[j] = stores[j + 1];
                }
                size--;
                return old;
            }
        }
        return null;
    }

    @Override
    public Store updateAddress(Address address, int id) {
        checkId(id);
        for (int i = 0; i < stores.length; i++) {
            if (stores[i].getId() == id) {
                Store store = stores[i];
                store.setAddress(address);
                return store;
            }
        }
        return null;
    }

    @Override
    public Store updateName(String name, int id) {
        checkId(id);
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getId() == id) {
                Store store = stores[i];
                store.setName(name);
                return store;
            }
        }
        return null;
    }

    @Override
    public Store[] getAll() {
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] != null) {
                count++;
            }
        }
        Store[] storesAll = new Store[count];
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] != null) {
                storesAll[count2] = stores[i];
                count2++;
            }
        }
        return storesAll;
    }

    @Override
    public Store getById(int id) {
        checkId(id);
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getId() == id) {
                return stores[i];
            }
        }
        return null;
    }

    @Override
    public Store getByName(String name) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i].getName().equals(name)) {
                return stores[i];
            }
        }
        return null;
    }

    @Override
    public Store getByAddress(Address address) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i].getAddress().equals(address)) {
                return stores[i];
            }
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        for (Store store : stores) {
            if (store == null) break;
            if (store.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Address address) {
        for (Store store : stores) {
            if (store == null) break;
            if (store.getAddress().equals(address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String street) {
        for (Store store : stores) {
            if (store == null) break;
            if (store.getName().equals(street)) {
                return true;
            }
        }
        return false;
    }
}
