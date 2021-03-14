package by.bookstore.service;

import by.bookstore.entity.*;

public interface OrderService {
    boolean save(Order order);

    Order deleteById(int id);

    Order deleteByOrder(Order order);

    Status updateStatus(Status status, int id);

    Order updateOrderType(int id, Store store, Address address, boolean isDelivery);

    Book[] updateAllBooks(Book[] books, int id);

    Address updateAddress(Address address, int id);

    Store updateStore(Store store, int id);

    boolean addBookById(Book book, int id);

    Book[] findAllBooksById(int id);

    Order findById(int id);

    Order[] findAll();

    Order[] findAllByUser(User user);

    Order[] findAllByStore(Store store);

    Order[] findAllByAddress(Address address);

}
