package by.bookstore.storage.db;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.storage.BookStorage;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBBookStorage extends AbstractStorage implements BookStorage {
    private static final String INSERT_BOOK = "insert into books values(default, ?, ?, ?, ?)";
    private static final String REMOVE_BY_ID = "delete from books where id =?";
    private static final String REMOVE_BY_TITLE = "delete from books where title = ?";
    private static final String SELECT_BY_ID = "select * from books b join authors a on b.author_id = a.id where b.id = ?";
    private static final String SELECT_BY_TITLE = "select * from books b join authors a on b.author_id = a.id where b.title = ?";
    private static final String UPDATE_TITLE_BY_ID = "update books set title = ? where id = ?";
    private static final String UPDATE_AUTHOR = "update books set author_id = ? where id = ?";
    private static final String UPDATE_DESC = "update books set description = ? where id = ?";
    private static final String UPDATE_PRICE = "update books set price = ? where id = ?";
    private static final String SELECT_ALL = "select * from books b join authors a on b.author_id = a.id";
    private static final String SELECT_ALL_FROM_AUTHOR_ID = "select * from books b join authors a on b.author_id = a.id where b.author_id = ?";
    private static final String SELECT_ALL_FROM_PRICE = "select * from books b join authors a on b.author_id = a.id where b.price = ?";


    public static void main(String[] args) {
        DBBookStorage dbBookStorage = new DBBookStorage();
        dbBookStorage.add(new Book("test", "test", new Author(3, "test"), new BigDecimal(22)));
        dbBookStorage.add(new Book("test2", "test2", new Author(4, "test"), new BigDecimal(33)));
    }

    @Override
    public Book add(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setInt(3, book.getPrice().intValue());
            preparedStatement.setInt(4, book.getAuthor().getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book remove(int id) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            int idB = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            int price = resultSet.getInt(4);
            int authorId = resultSet.getInt(6);
            String authorName = resultSet.getString(7);
            Book book = new Book(idB, title, description, new Author(authorId, authorName), new BigDecimal(price));
            preparedStatement1.close();

            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book remove(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TITLE);
            preparedStatement.setString(1, book.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idB = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            int price = resultSet.getInt(4);
            int authorId = resultSet.getInt(6);
            String authorName = resultSet.getString(7);
            Book book1 = new Book(idB, title, description, new Author(authorId, authorName), new BigDecimal(price));
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(REMOVE_BY_TITLE);
            preparedStatement1.setString(1, book.getTitle());
            preparedStatement1.execute();
            preparedStatement1.close();

            return book1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book updateTitle(String title, int id) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            int idB = resultSet.getInt(1);
            String titleb = resultSet.getString(2);
            String description = resultSet.getString(3);
            int price = resultSet.getInt(4);
            int authorId = resultSet.getInt(6);
            String authorName = resultSet.getString(7);
            Book book = new Book(idB, titleb, description, new Author(authorId, authorName), new BigDecimal(price));
            preparedStatement1.close();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TITLE_BY_ID);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();

            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book updateAuthor(Author author, int id) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            int idB = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            int price = resultSet.getInt(4);
            int authorId = resultSet.getInt(6);
            String authorName = resultSet.getString(7);
            Book book = new Book(idB, title, description, new Author(authorId, authorName), new BigDecimal(price));
            preparedStatement1.close();


            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR);
            preparedStatement.setInt(1, author.getId());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();

            return book;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book updateDescription(String desc, int id) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            int idB = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            int price = resultSet.getInt(4);
            int authorId = resultSet.getInt(6);
            String authorName = resultSet.getString(7);
            Book book = new Book(idB, title, description, new Author(authorId, authorName), new BigDecimal(price));
            preparedStatement1.close();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DESC);
            preparedStatement.setString(1, desc);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();

            return book;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book updateId(int id) {
        return null;
    }

    @Override
    public Book updatePrice(double price, int id) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            int idB = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            int priceb = resultSet.getInt(4);
            int authorId = resultSet.getInt(6);
            String authorName = resultSet.getString(7);
            Book book = new Book(idB, title, description, new Author(authorId, authorName), new BigDecimal(priceb));
            preparedStatement1.close();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRICE);
            preparedStatement.setInt(1, (int) price);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();

            return book;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book[] getAll() {
        try {
            List<Book> list = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idB = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                int priceb = resultSet.getInt(4);
                int authorId = resultSet.getInt(6);
                String authorName = resultSet.getString(7);
                list.add(idB, new Book(title, description, new Author(authorId, authorName), new BigDecimal(priceb)));
            }
            preparedStatement.close();
            return list.toArray(new Book[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idB = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String description = resultSet.getString(3);
            int price = resultSet.getInt(4);
            int authorId = resultSet.getInt(6);
            String authorName = resultSet.getString(7);
            Book book = new Book(idB, title, description, new Author(authorId, authorName), new BigDecimal(price));
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book[] getByTitle(String title) {
        try {
            List<Book> list = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TITLE);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idB = resultSet.getInt(1);
                String titleb = resultSet.getString(2);
                String description = resultSet.getString(3);
                int priceb = resultSet.getInt(4);
                int authorId = resultSet.getInt(6);
                String authorName = resultSet.getString(7);
                list.add(idB, new Book(titleb, description, new Author(authorId, authorName), new BigDecimal(priceb)));

            }
            preparedStatement.close();

            return list.toArray(new Book[0]);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book[] getByAuthor(Author author) {
        try {
            List<Book> list = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_AUTHOR_ID);
            preparedStatement.setInt(1, author.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idB = resultSet.getInt(1);
                String titleb = resultSet.getString(2);
                String description = resultSet.getString(3);
                int priceb = resultSet.getInt(4);
                int authorId = resultSet.getInt(6);
                String authorName = resultSet.getString(7);
                list.add(idB, new Book(titleb, description, new Author(authorId, authorName), new BigDecimal(priceb)));

            }

            preparedStatement.close();

            return list.toArray(new Book[0]);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Book[] getByPrice(double price) {
        try {
            List<Book> list = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_AUTHOR_ID);
            preparedStatement.setInt(1, (int) price);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idB = resultSet.getInt(1);
                String titleb = resultSet.getString(2);
                String description = resultSet.getString(3);
                int priceb = resultSet.getInt(4);
                int authorId = resultSet.getInt(6);
                String authorName = resultSet.getString(7);
                list.add(idB, new Book(titleb, description, new Author(authorId, authorName), new BigDecimal(priceb)));

            }

            preparedStatement.close();

            return list.toArray(new Book[0]);

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
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TITLE);
            preparedStatement.setString(1, book.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public int size() {
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
