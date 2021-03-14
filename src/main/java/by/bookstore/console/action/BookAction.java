package by.bookstore.console.action;

public interface BookAction {
    void save();

    void deleteByBook();

    void deleteById();

    void updateTittleById();

    void updateDescriptionById();

    void updateAuthorById();

    void updatePriceById();

    void findAll();

    void findAllByTitle();

    void findAllByAuthor();

    void findAllByPrice();

    void findById();
}
