package by.bookstore.storage;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;

public interface BookStorage {

    Book add(Book book);

    Book remove(int id);

    Book remove(Book book);

    Book updateTitle(String title, int id);

    Book updateAuthor(Author author, int id);

    Book updateDescription(String desc, int id);

    Book updateId(int id);

    Book updatePrice(double price, int id);

    Book[] getAll();

    Book getById(int id);

    Book[] getByTitle(String title);

    Book[] getByAuthor(Author author);

    Book[] getByPrice(double price);

    boolean contains(int id);

    boolean contains(Book book);

    int size();

}
