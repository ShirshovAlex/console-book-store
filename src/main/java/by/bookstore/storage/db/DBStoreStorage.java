package by.bookstore.storage.db;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
import by.bookstore.storage.StoreStorage;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBStoreStorage extends AbstractStorage implements StoreStorage {

    private static final String INSERT_STORE = "insert into stores values (default, ?, ?)";
    private static final String DELETE_STORE_BY_ID = "delete from stores where id = ?";
    private static final String DELETE_STORE_BY_NAME = "delete from stores where name = ?";
    private static final String UPDATE_ADDRESS_BY_ID = "update stores set address = ? where id =?";
    private static final String UPDATE_NAME_BY_ID = "update stores set name = ? where id =?";
    private static final String SELECT_ALL = "select * from stores";
    private static final String SELECT_ALL_BY_ID = "select * from stores where id = ?";
    private static final String SELECT_ALL_BY_NAME = "select * from stores where name = ?";
    private static final String SELECT_ALL_BY_ADDRESS = "select * from stores where address = ?";

    public static void main(String[] args) {
        DBStoreStorage dbStoreStorage = new DBStoreStorage();
//        dbStoreStorage.add(new Store("test", new Address("test")));
//        dbStoreStorage.add(new Store("test2", new Address("test2")));
//        dbStoreStorage.remove(1);
//        dbStoreStorage.remove(new Store("test2", new Address("test2")));
//        dbStoreStorage.updateAddress(new Address("new"), 3);
//        dbStoreStorage.updateName("new", 3);
//        System.out.println(Arrays.toString(dbStoreStorage.getAll()));
//        System.out.println(dbStoreStorage.getById(6));
//        System.out.println(dbStoreStorage.getByName("new"));
//        System.out.println(dbStoreStorage.getByAddress(new Address("test")));
//        System.out.println(dbStoreStorage.contains("new5"));
//        System.out.println(dbStoreStorage.contains(new Address("test5")));
    }

    @Override
    public Store add(Store store) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STORE);
            preparedStatement.setString(1, store.getName());
            preparedStatement.setString(2, store.getAddress().getStreet());
            preparedStatement.execute();
            preparedStatement.close();
            return store;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store remove(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STORE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store remove(Store store) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STORE_BY_NAME);
            preparedStatement.setString(1, store.getName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store updateAddress(Address address, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS_BY_ID);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store updateName(String name, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME_BY_ID);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store[] getAll() {
        try {
            List<Store> store = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                Store store1 = new Store(id, name, new Address(address));
                store.add(store1);
            }
            preparedStatement.close();
            return store.toArray(new Store[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id2 = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            Store store = new Store(id2, name, new Address(address));
            preparedStatement.close();
            return store;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store getByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_NAME);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id2 = resultSet.getInt(1);
            String name2 = resultSet.getString(2);
            String address = resultSet.getString(3);
            Store store = new Store(id2, name2, new Address(address));
            preparedStatement.close();
            return store;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store getByAddress(Address address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ADDRESS);
            preparedStatement.setString(1,address.getStreet());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id2 = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String address2 = resultSet.getString(3);
            Store store = new Store(id2, name, new Address(address2));
            preparedStatement.close();
            return store;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            preparedStatement.close();
            return next;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(Address address) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ADDRESS);
            preparedStatement.setString(1,address.getStreet());
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            preparedStatement.close();
            return next;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(String street) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_BY_NAME);
            preparedStatement.setString(1,street);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            preparedStatement.close();
            return next;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
