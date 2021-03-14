package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.storage.BookStorage;
import by.bookstore.storage.inmemory.InMemoryBookStorage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public class BookServiceImpl implements BookService {
    private final BookStorage bookStorage;

    public BookServiceImpl(@Qualifier("inMemoryBookStorage") BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    @Override
    public Book save(Book book) {
        if (bookStorage.contains(book)) return null;
        String title = book.getTitle().toUpperCase();
        book.setTitle(title);
        return bookStorage.add(book);
    }

    @Override
    public Book delete(int id) {
        if (bookStorage.contains(id)) {
            return bookStorage.remove(id);
        }
        return null;
    }

    @Override
    public Book delete(Book book) {
        if (bookStorage.contains(book)) {
            return bookStorage.remove(book);
        }
        return null;
    }

    @Override
    public Book updateTitleById(String title, int id) {
        if (bookStorage.contains(id)) {
            return bookStorage.updateTitle(title, id);
        }
        return null;
    }

    @Override
    public Book updateDescriptionById(String desc, int id) {
        if (bookStorage.contains(id)) {
            return bookStorage.updateDescription(desc, id);
        }
        return null;
    }

    @Override
    public Book updateAuthorById(Author author, int id) {
        if (bookStorage.contains(id)) {
            return bookStorage.updateAuthor(author, id);
        }
        return null;
    }

    @Override
    public Book updatePriceById(double price, int id) {
        if (bookStorage.contains(id)) {
            return bookStorage.updatePrice(price, id);
        }
        return null;
    }

    @Override
    public Book[] findAll() {
        return bookStorage.getAll();
    }

    @Override
    public Book[] findAllByTitle(String title) {
        return bookStorage.getByTitle(title);
    }

    @Override
    public Book[] findAllByAuthor(Author author) {
        return bookStorage.getByAuthor(author);
    }

    @Override
    public Book[] findAllByPrice(double price) {
        return bookStorage.getByPrice(price);
    }

    @Override
    public Book findById(int id) {
        if (bookStorage.contains(id)) {
            return bookStorage.getById(id);
        }
        return null;
    }
}
