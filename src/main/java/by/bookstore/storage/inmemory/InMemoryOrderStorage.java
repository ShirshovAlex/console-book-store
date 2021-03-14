package by.bookstore.storage.inmemory;

import by.bookstore.entity.*;
import by.bookstore.storage.OrderStorage;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class InMemoryOrderStorage implements OrderStorage {
    private static final Order[] orders = new Order[20];
    private int size = 0;

    @Override
    public boolean save(Order order) {
        if (orders.length == size) return false;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                orders[i] = order;
                size++;
                return true;
            }
        }
        return false;
    }

    private void checkId(int id) {
        if (size == 0 || id < 0) {
            throw new IllegalStateException();
        }
    }

    @Override
    public Order deleteById(int id) {
        checkId(id);
        Order old;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                old = orders[i];
                for (int j = 0; j < orders.length - 1; j++) {
                    orders[j] = orders[j + 1];
                }
                size--;
                return old;
            }
        }
        return null;
    }

    @Override
    public Order deleteByOrder(Order order) {
        Order old;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(order)) {
                old = orders[i];
                for (int j = 0; j < orders.length - 1; j++) {
                    orders[j] = orders[j + 1];
                }
                size--;
                return old;
            }
        }
        return null;
    }

    @Override
    public Status updateStatus(Status status, int id) {
        checkId(id);
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                orders[i].setStatus(status);
            }
        }
        return status;
    }

    @Override
    public Order updateOrderType(int id, Store store, Address address, boolean isDelivery) {
        checkId(id);
        for (Order order : orders) {
            if (order.getId() == id) {
                order.setStore(store);
                order.setAddress(address);
                order.setDelivery(isDelivery);
                return order;
            }
        }
        return null;
    }

    @Override
    public Book[] updateAllBooks(Book[] books, int id) {
        checkId(id);
        Book[] books1;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                books1 = orders[i].getBooks();
                orders[i].setBooks(books);
                return books1;
            }
        }
        return null;
    }

    @Override
    public Address updateAddress(Address address, int id) {
        checkId(id);
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                orders[i].setAddress(address);
                return address;
            }
        }
        return null;
    }

    @Override
    public Store updateStore(Store store, int id) {
        checkId(id);
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                orders[i].setStore(store);
                return store;
            }
        }
        return null;
    }

    @Override
    public boolean addBookById(Book book, int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                Book[] books = orders[i].getBooks();
                for (int i1 = 0; i1 < books.length; i1++) {
                    if (books[i] == null) {
                        books[i] = book;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Book[] findAllBooksById(int id) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                if (orders[i] == null) break;
                Book[] books = orders[i].getBooks();
                for (int i1 = 0; i1 < books.length; i1++) {
                    if (books[i1] != null) count++;
                }
                return Arrays.copyOf(books, count);
            }
        }
        return null;
    }

    @Override
    public Order findById(int id) {
        checkId(id);
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                return orders[i];
            }
        }
        return null;
    }

    @Override
    public Order[] findAll() {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) count++;
        }
        return Arrays.copyOf(orders, count);
    }

    @Override
    public Order[] findAllByUser(User user) {
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getUser().equals(user)) {
                count++;
            }
        }
        Order[] orderByUser = new Order[count];
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getUser().equals(user)) {
                orderByUser[count2++] = orders[i];
            }
        }
        return orderByUser;
    }

    @Override
    public Order[] findAllByStore(Store store) {
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getStore().equals(store)) {
                count++;
            }
        }
        Order[] orderByStore = new Order[count];
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getStore().equals(store)) {
                orderByStore[count2++] = orders[i];
            }
        }
        return orderByStore;
    }

    @Override
    public Order[] findAllByAddress(Address address) {
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getAddress().equals(address)) {
                count++;
            }
        }
        Order[] orderByAddress = new Order[count];
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;

            if (orders[i].getAddress().equals(address)) {
                orderByAddress[count2++] = orders[i];
            }
        }
        return orderByAddress;
    }

    @Override
    public boolean contains(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Order order) {
        for (Order value : orders) {
            if (value == null) break;
            if (value.equals(order)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }
}
