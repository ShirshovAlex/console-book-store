package by.bookstore.storage.db;

import by.bookstore.entity.Author;
import by.bookstore.storage.AuthorStorage;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DBAuthorStorage extends AbstractStorage implements AuthorStorage {
    private static final String INSERT_AUTHOR = "insert into authors values(default, ?)";
    private static final String DELETE_AUTHOR_BY_ID = "delete from authors where id = ?";
    private static final String DELETE_AUTHOR_BY_NAME = "delete from authors where name = ?";
    private static final String UPDATE_NAME_BY_ID = "update authors set name = ? where id = ?";
    private static final String SELECT_ALL = "select * from authors";
    private static final String SELECT_BY_ID = "select * from authors where id = ?";
    private static final String SELECT_BY_NAME = "select * from authors where name = ?";


    @Override
    public Author add(Author author) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHOR);
            preparedStatement.setString(1, author.getName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return author;
    }

    @Override
    public Author remove(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHOR_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Author remove(Author author) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHOR_BY_NAME);
            preparedStatement.setString(1, author.getName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Author update(String name, int id) {
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
    public Author[] getAll() {
        try {
            List<Author> authors = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String string = resultSet.getString(2);
                Author author = new Author(id, string);
                authors.add(author);
            }
            preparedStatement.close();
            return authors.toArray(new Author[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Author getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int id2 = resultSet.getInt(1);
            String name = resultSet.getString(2);

            Author author = new Author(id2, name);
            preparedStatement.close();
            return author;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Author[] getByName(String name) {
        try {
            List<Author> authors = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id2 = resultSet.getInt(1);
                String string = resultSet.getString(2);

                Author author = new Author(id2,string);
                authors.add(author);
            }
            preparedStatement.close();
            return authors.toArray(new Author[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
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
    public boolean contains(Author author) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
            preparedStatement.setString(1, author.getName());
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
