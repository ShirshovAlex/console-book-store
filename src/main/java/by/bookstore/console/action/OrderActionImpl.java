package by.bookstore.console.action;

import by.bookstore.console.Session;
import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.util.Reader;
import by.bookstore.console.util.Writer;
import by.bookstore.console.validator.AddressValidator;
import by.bookstore.console.validator.OrderValidator;
import by.bookstore.entity.*;
import by.bookstore.service.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class OrderActionImpl implements OrderAction {
    private final Reader reader;
    private final Writer writer;
    private final OrderService orderService;
    private final StoreService storeService;
    private final BookService bookService;
    private final UserService userService;

    public OrderActionImpl(Reader reader, Writer writer, OrderService orderService, StoreService storeService, BookService bookService, UserService userService) {
        this.reader = reader;
        this.writer = writer;
        this.orderService = orderService;
        this.storeService = storeService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public void save(Session session) {
        writer.write("Выберите тип");
        writer.write("0 - Exit");
        writer.write("1 - Delivery");
        writer.write("2 - PickUp");
        switch (reader.readInt()) {
            case 0:
                return;
            case 1:
                writer.write("Enter Address");
                String s = reader.readString();
                Address address = new Address(s);
                if (AddressValidator.validAddress(address)) {
                    Order newOrder = new Order(address, session.getBasket().getBooks(), session.getCurrentUser());
                    orderService.save(newOrder);
                    session.setBasket(new Basket(new Book[10]));
                } else {
                    writer.write("Invalid address!");
                }
                break;
            case 2:
                Store[] all = storeService.getAll();
                for (int i = 0; i < all.length; i++) {
                    writer.write((i + 1) + " " + all[i].getAddress().getStreet());
                }
                int i = reader.readInt() - 1;
                Store store = all[i];
                Order order = new Order(store, session.getBasket().getBooks(), session.getCurrentUser());
                orderService.save(order);
                session.setBasket(new Basket(new Book[10]));
                break;
        }
    }

    @Override
    public void deleteById() {
        writer.write("Enter id");
        int id = reader.readInt();
        if (!OrderValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        storeService.delete(id);
        writer.write("ok");
    }

    @Override
    public void deleteByOrder() {
        writer.write("Выберите Order");
        Order[] all = orderService.findAll();
        for (int i = 0; i < all.length; i++) {
            writer.write(i + 1 + " " + all[i]);
        }
        int i = reader.readInt() - 1;
        Order order = all[i];
        orderService.deleteByOrder(order);
    }

    @Override
    public void updateStatus() {
        writer.write("Enter id");
        int id = reader.readInt();
        if (!OrderValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        Status[] values = Status.values();
        for (int i = 0; i < values.length; i++) {
            writer.write((i + 1) + " " + values[i].name());
        }
        int i = reader.readInt() - 1;
        Status value = values[i];
        orderService.updateStatus(value, id);
        writer.write("ok");
    }

    @Override
    public void updateOrderType() {
        writer.write("Enter id");
        int id = reader.readInt();
        if (!OrderValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        writer.write("Выберите действие:");
        writer.write("0 - выход");
        writer.write("1 - доставка");
        writer.write("2 - самовывоз");
        switch (reader.readInt()) {
            case 0:
                return;
            case 1:
                writer.write("Enter address");
                String s = reader.readString();
                Address address = new Address(s);
                if (!AddressValidator.validAddress(address)) {
                    writer.write("Invalid address");
                    return;
                }
                orderService.updateOrderType(id, null, address, true);
                break;
            case 2:
                writer.write("Выберите Store");
                Store[] all = storeService.getAll();
                for (int i = 0; i < all.length; i++) {
                    writer.write(i + 1 + " " + all[i].getAddress() + " " + all[i].getName());
                }
                int i = reader.readInt() - 1;
                Store store = all[i];
                orderService.updateOrderType(id, store, null, false);
                break;
        }

    }

    @Override
    public void updateAllBooks(Book[] books) {
        writer.write("Enter id");
        int i = reader.readInt();
        if (!OrderValidator.validId(i)) {
            writer.write("Invalid id");
            return;
        }
        orderService.updateAllBooks(books, i);
    }

    @Override
    public void updateAddress() {
        writer.write("Enter id");
        int i = reader.readInt();
        if (!OrderValidator.validId(i)) {
            writer.write("Invalid id");
            return;
        }
        writer.write("Enter Address");
        String s = reader.readString();
        Address address = new Address(s);
        if (!AddressValidator.validAddress(address)) {
            writer.write("Invalid Address");
            return;
        }
        orderService.updateAddress(address, i);
    }

    @Override
    public void updateStore() {
        writer.write("Enter id");
        int i = reader.readInt();
        if (!OrderValidator.validId(i)) {
            writer.write("Invalid id");
            return;
        }
        writer.write("Choose store");
        Store[] all = storeService.getAll();
        for (int i1 = 0; i1 < all.length; i1++) {
            writer.write(i1 + 1 + " " + all[i1].getName());
        }
        int in = reader.readInt() - 1;
        Store store = all[in];
        orderService.updateStore(store, i);
    }

    @Override
    public void addBookById() {
        writer.write("Enter id");
        int i = reader.readInt();
        if (!OrderValidator.validId(i)) {
            writer.write("Invalid id");
            return;
        }

        writer.write("Choose book");
        Book[] all = bookService.findAll();
        for (int i1 = 0; i1 < all.length; i1++) {
            writer.write(i + 1 + " " + all[i].getAuthor() + " " + all[i].getTitle());
        }
        int book = reader.readInt() - 1;
        Book book1 = all[book];

        orderService.addBookById(book1, i);
    }

    @Override
    public void findAllBooksById() {
        writer.write("Enter id");
        int i = reader.readInt();
        if (!OrderValidator.validId(i)) {
            writer.write("Invalid id");
            return;
        }
        orderService.findAllBooksById(i);
    }

    @Override
    public void findById() {
        writer.write("Enter id");
        int id = reader.readInt();
        if (!OrderValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        orderService.findById(id);
    }

    @Override
    public void findAll() {
        Order[] all = orderService.findAll();
        for (int i = 0; i < all.length; i++) {
            writer.write("Order: " + all[i] + " " + Arrays.toString(all[i].getBooks()));
        }
    }

    @Override
    public void findAllByUser(Session session) {
        User[] all = userService.findAll();
        for (int i = 0; i < all.length; i++) {
                writer.write(i+1+" "+ all[i].getName());
        }
        int i1 = reader.readInt() - 1;
        User user = all[i1];
        Order[] allByUser = orderService.findAllByUser(user);
        for (int i = 0; i < allByUser.length; i++) {
            if (allByUser[i] == null) break;
            writer.write("Order: " + Arrays.toString(allByUser[i].getBooks()));
        }
    }

    @Override
    public void findAllByStore() {
        Store[] all = storeService.getAll();
        for (int i = 0; i < all.length; i++) {
            writer.write(i + 1 + " " + all[i].getName());
        }
        int i = reader.readInt() - 1;
        Store store = all[i];
        Order[] allByStore = orderService.findAllByStore(store);
        for (Order order : allByStore) {
            writer.write(Arrays.toString(order.getBooks()) + " " + order.getUser().getLogin());
        }
    }

    @Override
    public void findAllByAddress() {
        writer.write("Enter Address");
        String s = reader.readString();
        Address address = new Address(s);
        if (!AddressValidator.validAddress(address)) {
            writer.write("Invalid address");
            return;
        }
        Order[] allByAddress = orderService.findAllByAddress(address);
        for (Order byAddress : allByAddress) {
            System.out.println(byAddress.getAddress() + " " + byAddress.getUser().getLogin());
        }
    }
}
