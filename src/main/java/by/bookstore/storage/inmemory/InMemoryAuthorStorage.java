package by.bookstore.storage.inmemory;

import by.bookstore.entity.Author;
import by.bookstore.storage.AuthorStorage;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class InMemoryAuthorStorage implements AuthorStorage {

    private static final Author[] authors = new Author[50];
    private int size = 0;

    @Override
    public Author add(Author author) {
        if (authors.length == size)
            return null;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) {
                authors[i] = author;
                size++;
                return author;
            }
        }
        return null;
    }

    private void checkId(int id) {
        if (size == 0 || id < 0) throw new IllegalStateException();
    }

    @Override
    public Author remove(int id) {
        checkId(id);
        Author old;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getId() == id) {
                old = authors[i];
                for (int j = i; j < authors.length - 1; j++) {
                    authors[j] = authors[j + 1];
                }
                size--;
                return old;
            }
        }

        return null;
    }

    @Override
    public Author remove(Author author) {
        checkAuthor(author);
        Author old;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) break;
            if (authors[i].equals(author)) {
                old = authors[i];
                for (int j = i; j < authors.length - 1; j++) {
                    authors[j] = authors[j + 1];
                }
                size--;
                return old;
            }
        }
        return null;
    }

    private void checkAuthor(Author author) {
        if (author == null || size == 0) throw new NullPointerException();
    }

    @Override
    public Author update(String name, int id) {
        checkId(id);
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) break;
            if (authors[i].getId() == id) {
                Author author = authors[i];
                Author old = author;
                author.setName(name);
                return old;
            }
        }
        return null;
    }

    @Override
    public Author[] getAll() {
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] != null) {
                count++;
            }
        }
        Author[] authorsAll = new Author[count];
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] != null) {
                authorsAll[count2] = authors[i];
                count2++;
            }
        }
        return authorsAll;
    }

    @Override
    public Author getById(int id) {
        checkId(id);
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getId() == id) {
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public Author[] getByName(String name) {
        int count = 0;
        int count2 = 0;
        for (Author author : authors) {
            if (author.getName().equals(name)) {
                count++;
            }
        }
        if (count == 0) return null;
        Author[] nameAuthor = new Author[count];
        for (Author author : authors) {
            if (author.getName().equals(name)) {
                nameAuthor[count2++] = author;
            }
        }
        return nameAuthor;
    }

    @Override
    public boolean contains(int id) {
        for (Author author : authors) {
            if (author == null) break;
            if (author.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Author author) {
        for (Author value : authors) {
            if (value == null) break;
            if (value.equals(author)) {
                return true;
            }
        }
        return false;
    }
}
