package by.bookstore.storage.inmemory;

import by.bookstore.entity.Address;
import by.bookstore.entity.Role;
import by.bookstore.entity.User;
import by.bookstore.storage.UserStorage;
import org.springframework.stereotype.Component;


public class InMemoryUserStorage implements UserStorage {
    private static final User[] users = new User[20];

    static {
        users[0] = new User(1, "Admin", "admin", "admin", new Address("admin"), Role.ADMIN);
        users[1] = new User(2, "Denis", "denis", "denis", new Address("denis"), Role.USER);
    }

    private int size = 0;

    @Override
    public void add(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                size++;
                break;
            }
        }
    }

    @Override
    public String updateName(String name, int id) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getId() == id) {
                users[i].setName(name);
                break;
            }
        }
        return null;
    }

    @Override
    public String updatePassword(String pass, int id) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getId() == id) {
                users[i].setPassword(pass);
                break;
            }
        }
        return null;
    }

    private boolean checkNull(int i) {
        return users[i] == null;
    }

    @Override
    public Role updateRole(Role role, int id) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getId() == id) {
                users[i].setRole(role);
                break;
            }
        }
        return null;
    }

    @Override
    public Address updateAddress(Address address, int id) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getId() == id) {
                users[i].setAddress(address);
                break;
            }
        }
        return null;
    }

    @Override
    public void remove(User user) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) {
                break;
            }
            if (users[i].equals(user)) {
                unlinkUser(user);
                for (int j = i; j < users.length - 1; j++) {
                    users[j] = users[j + 1];
                }
                size--;
                break;
            }
        }
    }

    private void unlinkUser(User user) {
        user.setAddress(null);
        user.setRole(null);
        user.setPassword(null);
        user.setName(null);
        user.setLogin(null);
    }

    @Override
    public void remove(String login) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) {
                break;
            }
            if (users[i].getLogin().equals(login)) {
                unlinkUser(users[i]);
                for (int j = i; j < users.length - 1; j++) {
                    users[j] = users[j + 1];
                }
                size--;
                break;
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getId() == id) {
                unlinkUser(users[i]);
                for (int j = i; j < users.length - 1; j++) {
                    users[j] = users[j + 1];
                }
                size--;
                break;
            }
        }
    }

    @Override
    public User[] findAll() {
        User[] usersAll = new User[size];
        for (int i = 0; i < size; i++) {
            usersAll[i] = users[i];
        }
        return usersAll;
    }

    @Override
    public User findByLogin(String login) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) {
                break;
            }
            if (users[i].getLogin().equals(login)) {
                return users[i];
            }
        }
        return null;
    }

    @Override
    public User[] findAllByName(String name) {
        int count = 0;
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getName().equals(name)) {
                count++;
            }
        }

        int count2 = 0;
        User[] findByName = new User[count];
        for (int i = 0; i < users.length; i++) {
            if (users[i].getName().equals(name)) {
                findByName[count2++] = users[i];
            }
        }
        return findByName;
    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        return null;
    }

    @Override
    public User[] findByRole(Role role) {
        int count = 0;
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getRole().equals(role)) {
                count++;
            }
        }

        int count2 = 0;
        User[] findByRole = new User[count];
        for (int i = 0; i < users.length; i++) {
            if (users[i].getRole().equals(role)) {
                findByRole[count2++] = users[i];
            }
        }
        return findByRole;
    }

    @Override
    public boolean contains(int id) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String login) {
        for (int i = 0; i < users.length; i++) {
            if (checkNull(i)) break;
            if (users[i].getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
