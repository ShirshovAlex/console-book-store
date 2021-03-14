package by.bookstore.console.action;

import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.util.Reader;
import by.bookstore.console.util.Writer;
import by.bookstore.console.validator.StoreValidator;
import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
import by.bookstore.service.StoreService;
import by.bookstore.service.StoreServiceImpl;
import org.springframework.stereotype.Component;


public class StoreActionImpl implements StoreAction {
    private final Reader reader;
    private final Writer writer;
    private final StoreService storeService;

    public StoreActionImpl(Reader reader, Writer writer, StoreService storeService) {
        this.reader = reader;
        this.writer = writer;
        this.storeService = storeService;
    }

    @Override
    public void save() {
        writer.write("Введиет название магазина");
        String name = reader.readString();
        if (!StoreValidator.validName(name)) {
            writer.write("Invalid store name");
            return;
        }

        writer.write("Введите название улицы");
        String street = reader.readString();
        if (!StoreValidator.validName(street)) {
            writer.write("Invalid street name");
            return;
        }

        Store store = new Store(name, new Address(street));
        storeService.save(store);
        writer.write("Ok");
    }

    @Override
    public void deleteById() {
        writer.write("Введите id магазина");
        int id = reader.readInt();
        if (!StoreValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        storeService.delete(id);
        writer.write("Ok");
    }

    @Override
    public void deleteName() {
        writer.write("Введите название магазина который хотите удалить");
        String name = reader.readString();
        if (!StoreValidator.validName(name)) {
            writer.write("Invalid name");
            return;
        }
        storeService.delete(storeService.getByAddress(name));
        writer.write("Ok");
    }

    @Override
    public void updateAddress() {
        writer.write("Введите id");
        int id = reader.readInt();
        if (!StoreValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }

        writer.write("Введите новый Address");
        String address = reader.readString();
        if (!StoreValidator.validName(address)) {
            writer.write("Invalid Address");
            return;
        }
        storeService.updateAddress(new Address(address), id);
        writer.write("OK");
    }

    @Override
    public void updateName() {
        writer.write("Введите id");
        int id = reader.readInt();
        if (!StoreValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }

        writer.write("Введите новый name");
        String name = reader.readString();
        if (!StoreValidator.validName(name)) {
            writer.write("Invalid name");
            return;
        }

        storeService.updateName(name, id);
        writer.write("OK");

    }

    @Override
    public void findAll() {
        Store[] all = storeService.getAll();
        for (int i = 0; i < all.length; i++) {
            writer.write(i + 1 + " " + all[i].getName() + "  ---   " + all[i].getAddress());

        }
    }

    @Override
    public void findById() {
        writer.write("Введите id");
        int id = reader.readInt();
        if (!StoreValidator.validId(id)) {
            writer.write("Invalid id");
            return;
        }
        Store byId = storeService.getById(id);
        writer.write(byId.getId() + " " + byId.getName() + " " + byId.getAddress());
    }

    @Override
    public void findByAddress() {
        writer.write("Введите address");
        String address = reader.readString();
        if (!StoreValidator.validName(address)) {
            writer.write("Invalid Id");
            return;
        }

        Store byAddress = storeService.getByAddress(new Address(address));
        writer.write(byAddress.getId() + " " + byAddress.getName() + " " + byAddress.getAddress());

    }

    @Override
    public void findByName() {
        writer.write("Введите name");
        String name = reader.readString();
        if (!StoreValidator.validName(name)) {
            writer.write("Invalid name");
            return;
        }
        Store byName = storeService.getByAddress(name);
        writer.write(byName.getId() + " " + byName.getName() + " " + byName.getAddress());
    }
}
