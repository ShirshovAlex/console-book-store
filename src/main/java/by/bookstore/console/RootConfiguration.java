package by.bookstore.console;

import by.bookstore.console.action.*;
import by.bookstore.console.util.Reader;
import by.bookstore.console.util.Writer;
import by.bookstore.service.*;
import by.bookstore.storage.*;
import by.bookstore.storage.db.*;
import by.bookstore.storage.inmemory.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "by.bookstore")
public class RootConfiguration {

    @Bean
    public AuthorAction authorAction(AuthorService authorService, Reader reader, Writer writer) {
        return new AuthorActionImpl(authorService, reader, writer);
    }

//    @Bean(initMethod = "init", destroyMethod = "destroy")
//    public BookActionImpl bookAction(BookService bookService, AuthorService authorService, Reader reader, Writer writer) {
//        return new BookActionImpl(bookService, authorService, reader, writer);
//    }

    @Bean
    public OrderAction orderAction(Reader reader, Writer writer, OrderService orderService, StoreService storeService, BookService bookService, UserService userService) {
        return new OrderActionImpl(reader, writer, orderService, storeService, bookService, userService);
    }

    @Bean
    public StoreAction storeAction(Reader reader, Writer writer, StoreService storeService){
        return new StoreActionImpl(reader, writer, storeService);
    }

    @Bean
    public UserAction userAction(Writer writer, Reader reader, UserService userService){
        return new UserActionImpl(writer, reader, userService);
    }

    @Bean
    public AuthorService authorService(@Qualifier("inMemoryAuthorStorage") AuthorStorage authorStorage){
        return new AuthorServiceImpl(authorStorage);
    }

    @Bean
    public BookService bookService(@Qualifier("inMemoryBookStorage") BookStorage bookStorage){
        return new BookServiceImpl(bookStorage);
    }

    @Bean
    public OrderService orderService(@Qualifier("inMemoryOrderStorage") OrderStorage orderStorage){
        return new OrderServiceImpl(orderStorage);
    }

    @Bean
    public StoreService storeService(@Qualifier("inMemoryStoreStorage") StoreStorage storeStorage){
        return new StoreServiceImpl(storeStorage);
    }

    @Bean UserService userService(@Qualifier("inMemoryUserStorage") UserStorage userStorage){
        return new UserServiceImpl(userStorage);
    }

    @Bean
    public DBAuthorStorage dbAuthorStorage(){
        return new DBAuthorStorage();
    }

    @Bean
    public DBBookStorage dbBookStorage(){
        return new DBBookStorage();
    }

    @Bean
    public DBOrderRepository dbOrderRepository(){
        return new DBOrderRepository();
    }

    @Bean
    public DBStoreStorage dbStoreStorage(){
        return  new DBStoreStorage();
    }

    @Bean
    public DBUserStorage dbUserStorage(){
        return new DBUserStorage();
    }

    @Bean
    public InMemoryAuthorStorage inMemoryAuthorStorage(){
        return  new InMemoryAuthorStorage();
    }

    @Bean
    public InMemoryBookStorage inMemoryBookStorage(){
        return new InMemoryBookStorage();
    }

    @Bean
    public InMemoryOrderStorage inMemoryOrderStorage(){
        return new InMemoryOrderStorage();
    }

    @Bean
    public InMemoryStoreStorage inMemoryStoreStorage(){
        return new InMemoryStoreStorage();
    }

    @Bean
    public InMemoryUserStorage inMemoryUserStorage(){
        return new InMemoryUserStorage();
    }

    @Bean
    public List<String> nameList(){
        List<String> names = new ArrayList<>();
        names.add("Hello 1");
        names.add("Hello 2");
        names.add("Hello 3");
        return names;
    }

    @Bean
    public ConsoleApplication consoleApplication(Reader reader, Writer writer, BookAction bookAction,
                                                 AuthorAction authorAction,
                                                 UserAction userAction,
                                                 OrderAction orderAction,
                                                 UserService userService,
                                                 BookService bookService, StoreAction storeAction){
        return  new ConsoleApplication(reader,writer,
                bookAction,
                authorAction,
                storeAction,
                userAction,
                orderAction,
                userService,
                bookService);
    }
}
