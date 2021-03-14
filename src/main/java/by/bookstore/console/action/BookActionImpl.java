package by.bookstore.console.action;

import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.util.Reader;
import by.bookstore.console.util.Writer;
import by.bookstore.console.validator.BookValidator;
import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.service.AuthorService;
import by.bookstore.service.AuthorServiceImpl;
import by.bookstore.service.BookService;
import by.bookstore.service.BookServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;

@Component
public class BookActionImpl implements BookAction {
    private final BookService bookService;
    private final AuthorService authorService;
    private final Reader reader;
    private final Writer writer;

    public BookActionImpl(BookService bookService, AuthorService authorService, Reader reader, Writer writer) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.reader = reader;
        this.writer = writer;
    }

    @PostConstruct
    public void init(){
        System.out.println("Book Action init");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Book Action destroy");
    }

    @Override
    public void save() {
        String title = getString("Введите навзание книги");
        if (!BookValidator.validTitle(title)) {
            writer.write("Invalid book title");
            return;
        }
        String desc = getString("Введите описание");
        if (!BookValidator.validDesc(desc)) {
            writer.write("Invalid book description");
            return;
        }

        writer.write("Введите цену");
        double price = reader.readDouble();
        if (!BookValidator.validPrice(price)) {
            writer.write("Invalid book price");
            return;
        }

        Author author = getAuthor("Выберите автора из списка");
        if (author == null) {
            writer.write("Некорректный ввод");
            return;
        }
        Book book = new Book(title, desc, author, new BigDecimal(price));
        bookService.save(book);
    }

    private Author getAuthor(String s) {
        writer.write(s);
        Author[] all = authorService.findAll();
        for (int i = 0; i < all.length; i++) {
            writer.write("#" + (i + 1) + " " + all[i].getName());
        }

        int i = reader.readInt() - 1;

        if (i < all.length && i >= 0) {
            return all[i];
        }
        return null;
    }

    private String getString(String s) {
        writer.write(s);
        return reader.readString();
    }

    @Override
    public void deleteById() {
        int id = getInt("Введите id книги которую хотите удалить");
        if (!BookValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        bookService.delete(id);
    }

    @Override
    public void deleteByBook() {
        Book[] all = bookService.findAll();
        for (int i = 0; i < all.length; i++) {
            writer.write(i + 1 + " - " + all[i].getTitle());
        }
        int i = getInt("Выберите номер книги которую хотите удалить");
        Book book = all[i - 1];
        bookService.delete(book);
    }

    @Override
    public void updateTittleById() {
        String s = getString("Введите новый tittle");
        if (!BookValidator.validTitle(s)) {
            writer.write("Invalid title");
            return;
        }
        int i = getInt("введите id");
        if (!BookValidator.validId(i)) {
            writer.write("Invalid Id");
            return;
        }
        bookService.updateTitleById(s, i);

    }

    private int getInt(String s2) {
        writer.write(s2);
        return reader.readInt();
    }

    @Override
    public void updateDescriptionById() {
        String s = getString("Введите описание");
        if (!BookValidator.validDesc(s)) {
            writer.write("Invalid description");
            return;
        }
        int i = getInt("введите id");
        bookService.updateDescriptionById(s, i);

    }

    @Override
    public void updateAuthorById() {
        String s = getString("Введите автора");
        int i = getInt("введите id");
        if (!BookValidator.validId(i)) {
            writer.write("Invalid Id");
            return;
        }
        bookService.updateAuthorById(new Author(s), i);
    }

    @Override
    public void updatePriceById() {
        double aDouble = getDouble("Введите новую цену");
        if (!BookValidator.validPrice(aDouble)) {
            writer.write("Invalid price");
            return;
        }
        int id = getInt("Введите id");
        if (!BookValidator.validId(id)) {
            writer.write("Invalid Id");
            return;
        }
        bookService.updatePriceById(aDouble, id);
    }

    private double getDouble(String s) {
        writer.write(s);
        return reader.readDouble();
    }

    @Override
    public void findAll() {
        Book[] all = bookService.findAll();
        for (int i = 0; i < all.length; i++) {
            writer.write(i + 1 + " " + all[i].getTitle() + " " + all[i].getAuthor().getName());
        }
    }

    @Override
    public void findAllByTitle() {
        writer.write("Введите tittle");
        String s = reader.readString();
        if (!BookValidator.validTitle(s)) {
            writer.write("Invalid Title");
            return;
        }
        Book[] all = bookService.findAllByTitle(s);
        for (int i = 0; i < all.length; i++) {
            writer.write(i + 1 + " " + all[i].getTitle() + " " + all[i].getAuthor());
        }
    }

    @Override
    public void findAllByAuthor() {
        writer.write("Введите автора");
        String s = reader.readString();
        Book[] all = bookService.findAllByAuthor(new Author(s));
        for (int i = 0; i < all.length; i++) {
            writer.write(i + 1 + " " + all[i].getTitle());
        }
    }

    @Override
    public void findAllByPrice() {
        writer.write("Введите цену");
        double v = reader.readDouble();
        if (!BookValidator.validPrice(v)) {
            writer.write("Invalid Price");
            return;
        }
        Book[] all = bookService.findAllByPrice(v);
        for (int i = 0; i < all.length; i++) {
            writer.write(i + 1 + " " + all[i].getTitle());
        }
    }

    @Override
    public void findById() {
        writer.write("Введите id");
        int i = reader.readInt();
        if (!BookValidator.validId(i)) {
            writer.write("Invalid Id");
            return;
        }
        Book byId = bookService.findById(i);
        writer.write(byId.getId() + " " + byId.getTitle() + " " + byId.getAuthor() + " " + byId.getPrice() + " " + byId.getDescription());
    }
}
