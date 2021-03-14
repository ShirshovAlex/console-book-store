package by.bookstore.service;

import by.bookstore.entity.*;
import by.bookstore.storage.OrderStorage;
import by.bookstore.storage.inmemory.InMemoryOrderStorage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public class OrderServiceImpl implements OrderService {
    private final OrderStorage orderStorage;

    public OrderServiceImpl(@Qualifier("inMemoryOrderStorage") OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }

    @Override
    public boolean save(Order order) {
        if (!orderStorage.contains(order)) {
            return orderStorage.save(order);
        }
        return false;
    }

    @Override
    public Order deleteById(int id) {
        if (orderStorage.contains(id)) {
            return orderStorage.deleteById(id);
        }
        return null;
    }

    @Override
    public Order deleteByOrder(Order order) {
        if (orderStorage.contains(order)) {
            return orderStorage.deleteByOrder(order);
        }
        return null;
    }

    @Override
    public Status updateStatus(Status status, int id) {
        if (orderStorage.contains(id)) {
            orderStorage.updateStatus(status, id);
        }
        return null;
    }

    @Override
    public Order updateOrderType(int id, Store store, Address address, boolean isDelivery) {
        if (orderStorage.contains(id)) {
            if (isDelivery) {
                return orderStorage.updateOrderType(id, null, address, true);
            } else {
                return orderStorage.updateOrderType(id, store, null, false);
            }
        }
        return null;
    }

    @Override
    public Book[] updateAllBooks(Book[] books, int id) {
        if (orderStorage.contains(id)) {
            return orderStorage.updateAllBooks(books, id);
        }
        return null;
    }

    @Override
    public Address updateAddress(Address address, int id) {
        if (orderStorage.contains(id)) {
            return orderStorage.updateAddress(address, id);
        }
        return null;
    }

    @Override
    public Store updateStore(Store store, int id) {
        if (orderStorage.contains(id)) {
            return orderStorage.updateStore(store, id);
        }
        return null;
    }

    @Override
    public boolean addBookById(Book book, int id) {
        if (orderStorage.contains(id)) {
            return orderStorage.addBookById(book, id);
        }
        return false;
    }

    @Override
    public Book[] findAllBooksById(int id) {
        if (orderStorage.contains(id)) {
            return orderStorage.findAllBooksById(id);
        }
        return null;
    }

    @Override
    public Order findById(int id) {
        if (orderStorage.contains(id)) {
            return orderStorage.findById(id);
        }
        return null;
    }

    @Override
    public Order[] findAll() {
        return orderStorage.findAll();
    }

    @Override
    public Order[] findAllByUser(User user) {
        if (orderStorage.contains(user)) {
            return orderStorage.findAllByUser(user);
        }
        return null;
    }

    @Override
    public Order[] findAllByStore(Store store) {
        return orderStorage.findAllByStore(store);
    }

    @Override
    public Order[] findAllByAddress(Address address) {
        return orderStorage.findAllByAddress(address);
    }
}
