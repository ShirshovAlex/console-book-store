package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.storage.AuthorStorage;
import by.bookstore.storage.inmemory.InMemoryAuthorStorage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public class AuthorServiceImpl implements AuthorService {
    private final AuthorStorage authorStorage;

    public AuthorServiceImpl(@Qualifier("inMemoryAuthorStorage") AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    @Override
    public Author save(Author author) {
        if (authorStorage.contains(author)) return null;
        return authorStorage.add(author);
    }

    @Override
    public Author delete(int id) {
        if (authorStorage.contains(id)) {
            return authorStorage.remove(id);
        }
        return null;
    }

    @Override 
    public Author delete(Author author) {
        if (authorStorage.contains(author)) {
            return authorStorage.remove(author);
        }
        return null;
    }


    @Override
    public Author updateName(String name, int id) {
        if (authorStorage.contains(id)) {
            return authorStorage.update(name, id);
        }
        return null;
    }

    @Override
    public Author[] findAll() {
        return authorStorage.getAll();
    }

    @Override
    public Author findById(int id) {
        if (authorStorage.contains(id)) {
            return authorStorage.getById(id);
        }
        return null;
    }

    @Override
    public Author[] findByName(String name) {
        return authorStorage.getByName(name);
    }
}
