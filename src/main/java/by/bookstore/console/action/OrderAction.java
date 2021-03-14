package by.bookstore.console.action;

import by.bookstore.console.Session;
import by.bookstore.entity.Book;

public interface OrderAction {
    void save(Session session);

    void deleteById();

    void deleteByOrder();

    void updateStatus();

    void updateOrderType();

    void updateAllBooks(Book[] books);

    void updateAddress();

    void updateStore();

    void addBookById();

    void findAllBooksById();

    void findById();

    void findAll();

    void findAllByUser(Session session);

    void findAllByStore();

    void findAllByAddress();
}
