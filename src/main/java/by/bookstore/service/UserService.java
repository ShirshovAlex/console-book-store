package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.entity.Role;
import by.bookstore.entity.User;

public interface UserService {

    void add(User user);

    String updateName(String name, int id);

    String updatePassword(String pass, int id);

    Role updateRole(Role role, int id);

    Address updateAddress(Address address, int id);

    void remove(User user);

    void remove(String login);

    void remove(int id);

    User[] findAll();

    User findByLogin(String login);

    User[] findAllByName(String name);

    User findById(int id);

    User[] findByRole(Role role);


}
