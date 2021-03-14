package by.bookstore.console;

import by.bookstore.Application;
import by.bookstore.console.action.*;
import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.util.Reader;
import by.bookstore.console.util.Writer;
import by.bookstore.entity.Basket;
import by.bookstore.entity.Book;
import by.bookstore.entity.User;
import by.bookstore.service.BookService;
import by.bookstore.service.BookServiceImpl;
import by.bookstore.service.UserService;
import by.bookstore.service.UserServiceImpl;
import org.springframework.stereotype.Component;

public class ConsoleApplication implements Application {

    private static Session session;
    private final Reader reader;
    private final Writer writer;
    private final BookAction bookAction;
    private final AuthorAction authorAction;
    private final StoreAction storeAction;
    private final UserAction userAction;
    private final OrderAction orderAction;
    private final UserService userService;
    private final BookService bookService;

    public ConsoleApplication(Reader reader,
                              Writer writer,
                              BookAction bookAction,
                              AuthorAction authorAction,
                              StoreAction storeAction,
                              UserAction userAction,
                              OrderAction orderAction, UserService userService, BookService bookService) {
        this.reader = reader;
        this.writer = writer;
        this.bookAction = bookAction;
        this.authorAction = authorAction;
        this.storeAction = storeAction;
        this.userAction = userAction;
        this.orderAction = orderAction;
        this.userService = userService;
        this.bookService = bookService;
    }

    private void logout() {
        session = null;
    }

    private void addBookToBasket() {
        writer.write("Enter book");
        int i = reader.readInt() - 1;
        Book[] all = bookService.findAll();
        writer.write("Book info");
        writer.write("Title " + all[i].getTitle());
        writer.write("Author " + all[i].getAuthor().getName());
        writer.write("Description " + all[i].getDescription());
        writer.write("Price " + all[i].getPrice());
        writer.write("0 - Next");
        writer.write("1 - Add to basket");
        int i1 = reader.readInt();
        if (i1 == 1) {
            session.getBasket().addBook(all[i]);
        }
    }

    private void authorization() {
        writer.write("Enter login");
        String login = reader.readString();
        User byLogin = userService.findByLogin(login);
        writer.write("Enter password");
        String pass = reader.readString();
        if (byLogin != null && byLogin.getPassword().equals(pass)) {
            session = new Session(byLogin, new Basket(new Book[10]));
            writer.write("Auth ok");
        } else {
            writer.write("Auth error");
        }
    }

    @Override
    public void start() {
        while (true) {
            if (session == null) {
                showGuestMenu();
                switch (reader.readInt()) {
                    case 0:
                        return;
                    case 1:
                        userAction.save();
                        break;
                    case 2:
                        authorization();
                        break;
                }
            } else {
                writer.write("Welcome " + session.getCurrentUser().getName());
                switch (session.getCurrentUser().getRole()) {
                    case USER:
                        showUserMenu();
                        switch (reader.readInt()) {
                            case 0:
                                return;
                            case 1:
                                bookAction.findAll();
                                addBookToBasket();
                                break;
                            case 2:
                                bookAction.findAllByTitle();
                                break;
                            case 3:
                                bookAction.findAllByPrice();
                                break;
                            case 4:
                                bookAction.findAllByAuthor();
                                break;
                            case 5:
                                showAccountMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        continue;
                                    case 1:
                                        writer.write("Enter new name");
                                        String name = reader.readString();
                                        //todo add validator
                                        userService.updateName(name, session.getCurrentUser().getId());
                                        break;
                                    case 2:
                                        writer.write("Enter new password");
                                        String pass = reader.readString();
                                        userService.updatePassword(pass, session.getCurrentUser().getId());
                                        break;
                                    case 3:
                                        orderAction.findAllByUser(session);
                                        break;
                                    case 4:
                                        printBasket();
                                        showBasketMenu();
                                        switch (reader.readInt()) {
                                            case 0:
                                                continue;
                                            case 1:
                                                orderAction.save(session);
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case 6:
                                logout();
                                break;

                        }
                        break;
                    case ADMIN:
                        showAdminMenu();
                        switch (reader.readInt()) {
                            case 0:
                                return;
                            case 1:
                                showBookMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        continue;
                                    case 1:
                                        bookAction.save();
                                        break;
                                    case 2:
                                        bookAction.deleteByBook();
                                        break;
                                    case 3:
                                        bookAction.deleteById();
                                        break;
                                    case 4:
                                        bookAction.updateTittleById();
                                        break;
                                    case 5:
                                        bookAction.updateDescriptionById();
                                        break;
                                    case 6:
                                        bookAction.updatePriceById();
                                        break;
                                    case 7:
                                        bookAction.updateAuthorById();
                                        break;
                                    case 8:
                                        bookAction.findAll();
                                        break;
                                }
                                break;
                            case 2:
                                showAuthorMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        continue;
                                    case 1:
                                        authorAction.save();
                                        break;
                                    case 2:
                                        authorAction.deleteByAuthor();
                                        break;
                                    case 3:
                                        authorAction.deleteById();
                                        break;
                                    case 4:
                                        authorAction.updateName();
                                        break;
                                    case 5:
                                        authorAction.findAll();
                                        break;
                                    case 6:
                                        authorAction.findById();
                                        break;

                                }
                                break;
                            case 3:
                                showStoreMenu();
                                break;

                            case 4:
                                showOrderMenu();
                                switch (reader.readInt()) {
                                    case 0:
                                        continue;
                                    case 1:
                                        orderAction.findAll();
                                        break;
                                    case 2:
                                        orderAction.findAllByAddress();
                                        break;
                                    case 3:
                                        orderAction.findById();
                                        break;
                                    case 4:
                                        orderAction.save(session);
                                        break;
                                    case 5:
                                        orderAction.deleteById();
                                        break;
                                    case 6:
                                        orderAction.deleteByOrder();
                                        break;
                                    case 7:
                                        orderAction.updateStatus();
                                        break;
                                    case 8:
                                        orderAction.updateOrderType();
                                        break;
                                    case 9:
                                        Book[] all = bookService.findAll();
                                        orderAction.updateAllBooks(all);
                                        break;
                                    case 10:
                                        orderAction.updateAddress();
                                        break;
                                    case 11:
                                        orderAction.updateStore();
                                        break;
                                    case 12:
                                        orderAction.addBookById();
                                        break;
                                    case 13:
                                        orderAction.findAllBooksById();
                                        break;
                                    case 14:
                                        orderAction.findAllByUser(session);
                                        break;
                                    case 15:
                                        orderAction.findAllByStore();
                                        break;
                                }
                                break;
                            case 5:
                                logout();
                                break;
                        }
                        break;
                    case MODERATOR:

                        break;
                }
            }
        }
    }

    private void showOrderMenu() {
        writer.write("0 - Back");
        writer.write("1 - Show all orders");
        writer.write("2 - findAllByAddress");
        writer.write("3 - Show order by id");
        writer.write("4 - Save order");
        writer.write("5 - Delete by id");
        writer.write("6 - Delete by order");
        writer.write("7 - Update Status");
        writer.write("8 - updateOrderType");
        writer.write("9 - updateAllBooks");
        writer.write("10 - updateAddress");
        writer.write("11 - updateStore");
        writer.write("12 - addBookById");
        writer.write("13 - findAllBooksById");
        writer.write("14 - findAllByUser");
        writer.write("15 - findAllByStore");

    }

    private void printBasket() {
        Basket basket = session.getBasket();
        Book[] books = basket.getBooks();
        for (Book book : books) {
            if (book == null) break;
            writer.write(book.getTitle() + " " + book.getAuthor().getName());
        }
    }

    private void showBasketMenu() {
        writer.write("Books: " + session.getBasket().countBooks());
        writer.write("Total price: " + session.getBasket().totalPrice());
        writer.write("0 - Back");
        writer.write("1 - Create order");
    }

    private void showAccountMenu() {
        writer.write("0 - Back");
        writer.write("1 - Update name");
        writer.write("2 - Update password");
        writer.write("3 - Show orders");
        writer.write("4 - Show basket");
    }


    private void showModeratorMenu() {

    }

    private void showStoreMenu() {
        writer.write("0 - Back");
        writer.write("1 - Create Store");
        writer.write("2 - Delete Store");
        writer.write("3 - Delete Store By Id");
        writer.write("4 - Update Address");
        writer.write("5 - Update Street");
        writer.write("6 - Find All");
        writer.write("7 - Find By Id");
        writer.write("8 - Find By Street");
        writer.write("9 - Find By Address");
    }

    private void showAuthorMenu() {
        writer.write("0 - Back");
        writer.write("1 - Create Author");
        writer.write("2 - Delete Author");
        writer.write("3 - Delete Author By Id");
        writer.write("4 - Update Name");
        writer.write("5 - Find All");
        writer.write("6 - Find By Id");
    }

    private void showBookMenu() {
        writer.write("0 - Back");
        writer.write("1 - Create book");
        writer.write("2 - Delete book");
        writer.write("3 - Delete book by id");
        writer.write("4 - Update title");
        writer.write("5 - Update description");
        writer.write("6 - Update price");
        writer.write("7 - Update author");
        writer.write("8 - Find all");
    }

    private void showGuestMenu() {
        writer.write("0 - Back");
        writer.write("1 - Registration");
        writer.write("2 - Authorization");
        writer.write("3 - Find book by title");
        writer.write("4 - Find all");
    }

    private void showUserMenu() {
        writer.write("0 - Back");
        writer.write("1 - Find all");
        writer.write("2 - Find by title");
        writer.write("3 - Find by price");
        writer.write("4 - Find by author");
        writer.write("5 - Account");
        writer.write("6 - Logout");

    }


    private void showAdminMenu() {
        writer.write("0 - Exit");
        writer.write("1 - Book menu");
        writer.write("2 - Author menu");
        writer.write("3 - Store menu");
        writer.write("4 - Orders menu");
        writer.write("5 - Logout");
    }
}
