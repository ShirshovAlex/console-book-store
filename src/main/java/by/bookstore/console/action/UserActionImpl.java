package by.bookstore.console.action;

import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.util.Reader;
import by.bookstore.console.util.Writer;
import by.bookstore.console.validator.UserValidator;
import by.bookstore.entity.Address;
import by.bookstore.entity.Role;
import by.bookstore.entity.User;
import by.bookstore.service.UserService;
import by.bookstore.service.UserServiceImpl;
import org.springframework.stereotype.Component;


public class UserActionImpl implements UserAction {

    private Writer writer;
    private Reader reader;
    private UserService userService;

    public UserActionImpl(Writer writer, Reader reader, UserService userService) {
        this.writer = writer;
        this.reader = reader;
        this.userService = userService;
    }

    @Override
    public void save() {
        writer.write("Enter name");
        String name = reader.readString();
        if (!UserValidator.validName(name)) {
            writer.write("Invalid name");
            return;
        }
        writer.write("Enter login");
        String login = reader.readString();
        if (!UserValidator.validName(login)) {
            writer.write("Invalid login");
            return;
        }
        writer.write("Enter password");
        String pass = reader.readString();
        if (!UserValidator.validName(pass)) {
            writer.write("Invalid password");
            return;
        }
        writer.write("Enter address");
        String address = reader.readString();
        if (!UserValidator.validName(address)) {
            writer.write("Invalid Address");
            return;
        }
        if (login.equals("admin")) {
            userService.add(new User(name, login, pass, new Address(address), Role.ADMIN));
            return;
        }
        userService.add(new User(name, login, pass, new Address(address), Role.USER));
    }

    @Override
    public void updateName() {
        writer.write("Enter new Name");
        String name = reader.readString();
        if (!UserValidator.validName(name)) {
            writer.write("Invalid name");
            return;
        }
        writer.write("Enter id");
        int id = reader.readInt();
        if (!UserValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        userService.updateName(name, id);
    }

    @Override
    public void updatePassword() {
        writer.write("Enter new password");
        String pass = reader.readString();
        if (!UserValidator.validName(pass)) {
            writer.write("Invalid password");
            return;
        }
        writer.write("Enter id");
        int id = reader.readInt();
        if (!UserValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        userService.updatePassword(pass, id);
    }

    @Override
    public void updateRole() {
        writer.write("Enter id");
        int id = reader.readInt();
        if (!UserValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }

        writer.write("Enter new Role");
        Role[] values = Role.values();
        for (int i = 0; i < values.length; i++) {
            writer.write((i + 1) + " " + values[i].name());
        }
        int i = reader.readInt() - 1;
        userService.updateRole(values[i], id);
    }

    @Override
    public void updateAddress() {
        writer.write("Enter new Address");
        String address = reader.readString();
        if (!UserValidator.validName(address)) {
            writer.write("Invalid address");
            return;
        }
        writer.write("Enter id");
        int id = reader.readInt();
        if (!UserValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        userService.updateAddress(new Address(address), id);
    }

    @Override
    public void deleteByLogin() {
        writer.write("Enter Login");
        String login = reader.readString();
        if (!UserValidator.validName(login)) {
            writer.write("Invalid login");
            return;
        }
        userService.remove(login);
    }

    @Override
    public void deleteById() {
        writer.write("Enter id");
        int id = reader.readInt();
        if (!UserValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        userService.remove(id);
    }

    @Override
    public void deleteByUser() {
        writer.write("Enter user");
        String user = reader.readString();
        if (!UserValidator.validName(user)) {
            writer.write("Invalid user");
            return;
        }
        userService.remove(user);
    }

    @Override
    public void findAll() {
        userService.findAll();
    }

    @Override
    public void findAllByName() {
        writer.write("Enter name");
        String nameAll = reader.readString();
        if (!UserValidator.validName(nameAll)) {
            writer.write("Invalid name");
            return;
        }
        userService.findAllByName(nameAll);
    }

    @Override
    public void findAllByRole() {
        Role[] values = Role.values();
        for (int i = 0; i < values.length; i++) {
            writer.write(i + 1 + " " + values[i]);
        }
        writer.write("Choose role");
        int i = reader.readInt() - 1;
        User[] byRole = userService.findByRole(values[i]);
        for (int j = 0; j < byRole.length; j++) {
            writer.write(byRole[j].getName());
        }
    }

    @Override
    public void findById() {
        writer.write("Enter id");
        int id = reader.readInt();
        if (!UserValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        userService.findById(id);
    }

    @Override
    public void findByLogin() {
        writer.write("Enter login");
        String login = reader.readString();
        if (!UserValidator.validName(login)) {
            writer.write("Invalid login");
            return;
        }
        userService.findByLogin(login);
    }
}
