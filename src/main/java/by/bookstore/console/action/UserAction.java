package by.bookstore.console.action;

public interface UserAction {
    void save();

    void updateName();

    void updatePassword();

    void updateRole();

    void updateAddress();

    void deleteByLogin();

    void deleteById();

    void deleteByUser();

    void findAll();

    void findAllByName();

    void findAllByRole();

    void findById();

    void findByLogin();
}
