package by.bookstore.storage.db;

import by.bookstore.entity.Address;
import by.bookstore.entity.Role;
import by.bookstore.entity.User;
import by.bookstore.storage.UserStorage;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBUserStorage extends AbstractStorage implements UserStorage {
    private static final String INSERT_USER = "insert into users values(default, ?, ?, ?, ?, ?)";
    private static final String SELECT_ROLE_BY_ROLE = "select r.id from roles r where r.role = ?";
    private static final String UPDATE_NAME_BY_ID = "update users set name = ? where id = ?";
    private static final String SELECT_NAME_BY_ID = "select u.name from users u where id = ?";
    private static final String SELECT_PASSWORD_BY_ID = "select u.password from users u where id = ?";
    private static final String UPDATE_PASSWORD_BY_ID = "update users set password = ? where id = ?";
    private static final String SELECT_USER_ROLE_BY_ID = "select u.role_id from users u where u.id =?";
    private static final String SELECT_ROLE_BY_ID = "select r.role from roles r where r.id =?";
    private static final String UPDATE_ROLE_BY_ID = "update users set role_id = ? where id =?";
    private static final String SELECT_ROLE_NAME_BY_USER_ID = "select r.role from users u join roles r on r.id = u.role_id where u.id =?";
    private static final String SELECT_ADDRESS_BY_ID = "select u.address from users u where id =?";
    private static final String UPDATE_ADDRESS_BY_ID = "update users set address = ? where id = ?";
    private static final String REMOVE_USER_BY_NAME = "delete from users where name = ?";
    private static final String REMOVE_USER_BY_LOGIN = "delete from users where login = ?";
    private static final String REMOVE_USER_BY_ID = "delete from users where id = ?";
    private static final String FIND_ALL = "select * from users u join roles r on r.id = u.role_id";
    private static final String FIND_ALL_BY_NAME = "select * from users u join roles r on r.id = u.role_id where name = ?";
    private static final String FIND_ALL_BY_ROLE = "select * from users u join roles r on r.id = u.role_id where r.role = ?";
    private static final String FIND_BY_LOGIN = "select * from users u join roles r on r.id = u.role_id where u.login = ?";
    private static final String FIND_BY_ID = "select * from users u join roles r on r.id = u.role_id where u.id = ?";


    public static void main(String[] args) {
        DBUserStorage dbUserStorage = new DBUserStorage();
//        dbUserStorage.add(new User("Test2", "test2", "test2", new Address("test address"), Role.USER));
//        System.out.println(dbUserStorage.updateName("new2", 5));
//        System.out.println(dbUserStorage.updateRole(Role.ADMIN, 5));
//        System.out.println(dbUserStorage.updateAddress(new Address("newStreet"), 5));
//        dbUserStorage.remove(new User("new2", null, null, null, null));
//        System.out.println(dbUserStorage.findByLogin("test2"));
//        System.out.println(Arrays.toString(dbUserStorage.findAllByName("Admin")));
//        System.out.println(dbUserStorage.findById(6));
//        System.out.println(Arrays.toString(dbUserStorage.findByRole(Role.ADMIN)));
        System.out.println(dbUserStorage.contains(6));
        System.out.println(dbUserStorage.contains(8));
        System.out.println(dbUserStorage.contains("admin"));
        System.out.println(dbUserStorage.contains("admin2"));
    }

    @Override
    public void add(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ROLE);
            preparedStatement.setString(1, user.getRole().name());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int roleId = resultSet.getInt(1);
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_USER);
            preparedStatement1.setString(1, user.getName());
            preparedStatement1.setString(2, user.getLogin());
            preparedStatement1.setString(3, user.getPassword());
            preparedStatement1.setString(4, user.getAddress().getStreet());
            preparedStatement1.setInt(5, roleId);

            preparedStatement1.execute();
            preparedStatement1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String updateName(String name, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAME_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String string = resultSet.getString(1);
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(UPDATE_NAME_BY_ID);
            preparedStatement1.setString(1, name);
            preparedStatement1.setInt(2, id);
            preparedStatement1.execute();
            preparedStatement1.close();

            return string;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String updatePassword(String pass, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASSWORD_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String string = resultSet.getString(1);
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(UPDATE_PASSWORD_BY_ID);
            preparedStatement1.setString(1, pass);
            preparedStatement1.setInt(2, id);
            preparedStatement1.execute();
            preparedStatement1.close();

            return string;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Role updateRole(Role role, int id) {
        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ROLE_BY_ID);
//            preparedStatement.setInt(1,id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            int roleId = resultSet.getInt(1);
//
//            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_ROLE_BY_ID);
//            preparedStatement1.setInt(1, roleId);
//            ResultSet resultSet1 = preparedStatement1.executeQuery();
//            resultSet1.next();
//            String roleName = resultSet1.getString(1);
//            Role old = Role.valueOf(roleName);

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_NAME_BY_USER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Role old = Role.valueOf(resultSet.getString(1));

            preparedStatement.close();

            PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_ROLE_BY_ROLE);
            preparedStatement2.setString(1, role.name());
            ResultSet resultSet1 = preparedStatement2.executeQuery();
            resultSet1.next();
            int roleId = resultSet1.getInt(1);

            preparedStatement2.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(UPDATE_ROLE_BY_ID);
            preparedStatement1.setInt(1, roleId);
            preparedStatement1.setInt(2, id);
            preparedStatement1.execute();

            preparedStatement1.close();
            return old;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Address updateAddress(Address address, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESS_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String string = resultSet.getString(1);
            Address address1 = new Address(string);
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(UPDATE_ADDRESS_BY_ID);
            preparedStatement1.setString(1, address.getStreet());
            preparedStatement1.setInt(2, id);
            preparedStatement1.execute();
            preparedStatement1.close();

            return address1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_NAME);
            preparedStatement.setString(1, user.getName());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void remove(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public User[] findAll() {
        try {
            List<User> users = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String login = resultSet.getString(3);
                String password = resultSet.getString(4);
                String address = resultSet.getString(5);
                Role role = Role.valueOf(resultSet.getString(8));
                users.add(new User(id, name, login, password, new Address(address), role));
            }
            return users.toArray(new User[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByLogin(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String login2 = resultSet.getString(3);
            String password = resultSet.getString(4);
            String address = resultSet.getString(5);
            Role role = Role.valueOf(resultSet.getString(8));
            User user = new User(id, name, login2, password, new Address(address), role);
            preparedStatement.close();
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User[] findAllByName(String name) {
        try {
            List<User> users = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {
                int id = resultSet.getInt(1);
                String name2 = resultSet.getString(2);
                String login = resultSet.getString(3);
                String password = resultSet.getString(4);
                String address = resultSet.getString(5);
                Role role = Role.valueOf(resultSet.getString(8));
                users.add(new User(id, name2, login, password, new Address(address), role));
            }
            return users.toArray(new User[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id2 = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String login2 = resultSet.getString(3);
            String password = resultSet.getString(4);
            String address = resultSet.getString(5);
            Role role = Role.valueOf(resultSet.getString(8));
            User user = new User(id2, name, login2, password, new Address(address), role);
            preparedStatement.close();
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User[] findByRole(Role role) {
        try {
            List<User> users = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_ROLE);
            preparedStatement.setString(1, role.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {
                int id = resultSet.getInt(1);
                String name2 = resultSet.getString(2);
                String login = resultSet.getString(3);
                String password = resultSet.getString(4);
                String address = resultSet.getString(5);
                Role role2 = Role.valueOf(resultSet.getString(8));
                users.add(new User(id, name2, login, password, new Address(address), role2));
            }
            return users.toArray(new User[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
