package by.bookstore.console.action;

public interface AuthorAction {
    void save();

    void deleteById();

    void deleteByAuthor();

    void updateName();

    void findAll();

    void findByName();

    void findById();
}
