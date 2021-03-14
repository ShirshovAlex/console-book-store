package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;

public interface BookService {
    Book save(Book book);

    Book delete(int id);

    Book delete(Book book);

    Book updateTitleById(String title, int id);

    Book updateDescriptionById(String desc, int id);

    Book updateAuthorById(Author author, int id);

    Book updatePriceById(double price, int id);

    Book[] findAll();

    Book[] findAllByTitle(String title);

    Book[] findAllByAuthor(Author author);

    Book[] findAllByPrice(double price);

    Book findById(int id);
}
