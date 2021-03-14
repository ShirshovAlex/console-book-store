package by.bookstore.console.action;

import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.util.Reader;
import by.bookstore.console.util.Writer;
import by.bookstore.console.validator.AuthorValidator;
import by.bookstore.entity.Author;
import by.bookstore.service.AuthorService;
import by.bookstore.service.AuthorServiceImpl;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class AuthorActionImpl implements AuthorAction, BeanNameAware {
    private String myName;

    private AuthorService authorService;
    private Reader reader;
    private Writer writer;

    public AuthorActionImpl(AuthorService authorService, Reader reader, Writer writer) {
        this.authorService = authorService;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void save() {
        writer.write("Введите имя Автора");
        String s = reader.readString();
        if (!AuthorValidator.validName(s)) {
            writer.write("Некорректное имя автора");
            return;
        }
        authorService.save(new Author(s));
    }

    @Override
    public void deleteById() {
        writer.write("Введите id");
        int i = reader.readInt();
        if (!AuthorValidator.validId(i)) {
            writer.write("Нет такого id");
            return;
        }
        authorService.delete(i);
    }

    @Override
    public void deleteByAuthor() {
        Author[] all = authorService.findAll();
        for (int i = 0; i < all.length; i++) {
            writer.write("#" + (i + 1) + " " + all[i].getName());
        }
        int i = reader.readInt() - 1;
        if (i < all.length && i >= 0) {
            Author author = all[i];
            authorService.delete(author);
            return;
        }
        writer.write("Некорректный ввод");
    }

    @Override
    public void updateName() {
        writer.write("Введите имя");
        String s = reader.readString();
        if (!AuthorValidator.validName(s)) {
            writer.write("Некорректное имя");
            return;
        }
        writer.write("Введите id");
        int i = reader.readInt();
        if (!AuthorValidator.validId(i)) {
            writer.write("Некорректный id");
            return;
        }
        authorService.updateName(s, i);
        writer.write("ок");
    }

    @Override
    public void findAll() {
        Author[] all = authorService.findAll();
        for (int i = 0; i < all.length; i++) {
            writer.write("#" + (i + 1) + " " + all[i].getName());
        }
    }

    @Override
    public void findByName() {
        writer.write("Введите имя");
        String s = reader.readString();
        if (!AuthorValidator.validName(s)) {
            writer.write("Некорректное имя");
            return;
        }
        Author[] byName = authorService.findByName(s);
        if (byName == null) {
            writer.write("Авторов не найдено");
            return;
        }
        for (int i = 0; i < byName.length; i++) {
            writer.write("#" + (i + 1) + " " + byName[i].getName());
        }
    }

    @Override
    public void findById() {
        writer.write("Введите id");
        int i = reader.readInt();
        if (!AuthorValidator.validId(i)) {
            writer.write("Некорректный id");
            return;
        }
        Author byId = authorService.findById(i);
        writer.write(byId.getName());
    }

    @Override
    public void setBeanName(String s) {
        this.myName = s;
    }
}
