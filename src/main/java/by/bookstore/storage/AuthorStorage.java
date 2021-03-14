package by.bookstore.storage;

import by.bookstore.entity.Author;

public interface AuthorStorage {
    Author add(Author author);

    Author remove(int id);

    Author remove(Author author);

    Author update(String name, int id);

    Author[] getAll();

    Author getById(int id);

    Author[] getByName(String name);

    boolean contains(int id);

    boolean contains(Author author);
}
