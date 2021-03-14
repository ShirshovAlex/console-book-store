package by.bookstore.storage.db;

import by.bookstore.entity.*;
import by.bookstore.storage.OrderStorage;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBOrderRepository extends AbstractStorage implements OrderStorage {
    private static final String INSERT_ORDER = "insert into orders values (default, ?, ?, ?, (select s.id from statuses s where s.status = ?), ?) returning id";
    private static final String INSERT_BOOK_BY_ORDER_ID = "insert into order_book values(?, ?)";
    private static final String DELETE_ORDER_BY_ID = "delete from orders o where o.id = ?";
    private static final String DELETE_BOOKS_BY_ORDER_ID = "delete from order_book ob where ob.order_id = ?";
    private static final String UPDATE_ORDER_STATUS_BY_ID = "update orders SET status_id = (select s.id from statuses s where s.status = ?) where id = ?";
    private static final String UPDATE_ORDER_ADDRESS_BY_ID = "update orders SET address = ? where id = ?";
    private static final String UPDATE_STORE_ORDER_BY_ID = "update orders SET store_id = ? where id = ?";
    private static final String SELECT_ALL_BOOKS_BY_ORDER_ID = "select * from books b join authors a on b.author_id = a.id join order_book ob on b.id = ob.book_id where order_id = ?";


    public static void main(String[] args) {
        DBOrderRepository dbOrderRepository = new DBOrderRepository();

//        List<Book> books = new ArrayList<>();
//        books.add(new Book(1, null, null, null, null));
//        books.add(new Book(2, null, null, null, null));
//
//        Store store = new Store(3, null, null);
//        Address address = new Address("new test address");
//
//        User user = new User(6, null, null, null, null, null);
//
//        Order order = new Order(store, books.toArray(new Book[0]), user);
//        Order order2 = new Order(address, books.toArray(new Book[0]), user);
//        dbOrderRepository.save(order2);

        dbOrderRepository.updateStatus(Status.CLOSE, 1);
    }

    @Override
    public boolean save(Order order) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            if (order.isDelivery()) {
                preparedStatement.setString(1, order.getAddress().getStreet());
                preparedStatement.setInt(2, 0);
            } else {
                preparedStatement.setString(1, null);
                preparedStatement.setInt(2, order.getStore().getId());
            }
            preparedStatement.setInt(3, order.getUser().getId());
            preparedStatement.setString(4, order.getStatus().name());
            preparedStatement.setBoolean(5, order.isDelivery());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int orderId = resultSet.getInt(1);
            preparedStatement.close();

            Book[] books = order.getBooks();

            if (order.getAddress().getStreet().equals("test")) {
                throw new SQLException();
            }

            for (Book book : books) {
                PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_BOOK_BY_ORDER_ID);
                preparedStatement1.setInt(1, orderId);
                preparedStatement1.setInt(2, book.getId());
                preparedStatement1.execute();
                preparedStatement1.close();
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException throwables) {
            try {
                connection.setAutoCommit(true);
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Order deleteById(int id) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_BOOKS_BY_ORDER_ID);
            preparedStatement1.setInt(1, id);
            preparedStatement1.execute();
            preparedStatement1.close();

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException throwables) {
            try {
                connection.setAutoCommit(true);
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order deleteByOrder(Order order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Status updateStatus(Status status, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS_BY_ID);
            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order updateOrderType(int id, Store store, Address address, boolean isDelivery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Book[] updateAllBooks(Book[] books, int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Address updateAddress(Address address, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_ADDRESS_BY_ID);
            preparedStatement.setString(1,address.getStreet());
            preparedStatement.setInt(2,id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Store updateStore(Store store, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STORE_ORDER_BY_ID);
            preparedStatement.setInt(1,store.getId());
            preparedStatement.setInt(2,id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addBookById(Book book, int id) {
        return false;
    }

    @Override
    public Book[] findAllBooksById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS_BY_ORDER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Book> list = new ArrayList<>();

            while (resultSet.next()) {
                int bookId = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                int price = resultSet.getInt(4);
                int authorId = resultSet.getInt(6);
                String authorName = resultSet.getString(7);

                Author author = new Author(authorId, authorName);
                Book book = new Book(bookId, title, description, author, new BigDecimal(price));
                list.add(book);
            }

            return list.toArray(new Book[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public Order[] findAll() {
        return new Order[0];
    }

    @Override
    public Order[] findAllByUser(User user) {
        return new Order[0];
    }

    @Override
    public Order[] findAllByStore(Store store) {
        return new Order[0];
    }

    @Override
    public Order[] findAllByAddress(Address address) {
        return new Order[0];
    }

    @Override
    public boolean contains(int id) {
        return false;
    }

    @Override
    public boolean contains(Order order) {
        return false;
    }

    @Override
    public boolean contains(User user) {
        return false;
    }
}
