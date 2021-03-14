package by.bookstore.service;

import by.bookstore.entity.Author;

public interface AuthorService {
    Author save(Author author);

    Author delete(int id);

    Author delete(Author author);
    //Author delete(String name);

    Author updateName(String name, int id);

    Author[] findAll();

    Author findById(int id);

    Author[] findByName(String name);

}
