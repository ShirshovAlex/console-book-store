package by.bookstore.console.validator;


public class StoreValidator {
    public static boolean validId(int id) {
        return id > 0;
    }

    public static boolean validName(String name) {
        return name.length() > 2;
    }
}
