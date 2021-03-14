package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.entity.Role;
import by.bookstore.entity.User;
import by.bookstore.storage.UserStorage;
import by.bookstore.storage.inmemory.InMemoryUserStorage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    public UserServiceImpl(@Qualifier("inMemoryUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void add(User user) {
        if (userStorage.contains(user.getId())) {
            return;
        }
        userStorage.add(user);
    }

    @Override
    public String updateName(String name, int id) {
        if (userStorage.contains(id)) {
            userStorage.updateName(name, id);
        }
        return null;
    }

    @Override
    public String updatePassword(String pass, int id) {
        if (userStorage.contains(id)) {
            userStorage.updatePassword(pass, id);
        }
        return null;
    }

    @Override
    public Role updateRole(Role role, int id) {
        if (userStorage.contains(id)) {
            userStorage.updateRole(role, id);
        }
        return null;
    }

    @Override
    public Address updateAddress(Address address, int id) {
        if (userStorage.contains(id)) {
            userStorage.updateAddress(address, id);
        }
        return null;
    }

    @Override
    public void remove(User user) {
        if (userStorage.contains(user.getId())) {
            userStorage.remove(user);
        }

    }

    @Override
    public void remove(String login) {
        if (userStorage.contains(login)) {
            userStorage.remove(login);
        }
    }

    @Override
    public void remove(int id) {
        if (userStorage.contains(id)) {
            userStorage.remove(id);
        }
    }

    @Override
    public User[] findAll() {
        return userStorage.findAll();
    }

    @Override
    public User findByLogin(String login) {
        if (userStorage.contains(login)) {
            return userStorage.findByLogin(login);
        }
        return null;
    }

    @Override
    public User[] findAllByName(String name) {
        return userStorage.findAllByName(name);
    }

    @Override
    public User findById(int id) {
        if (userStorage.contains(id)) {
            return userStorage.findById(id);
        }
        return null;
    }

    @Override
    public User[] findByRole(Role role) {
        return userStorage.findByRole(role);
    }
}
