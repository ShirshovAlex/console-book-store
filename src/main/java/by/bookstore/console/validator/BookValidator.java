package by.bookstore.console.validator;

public class BookValidator {
    public static boolean validId(int id) {
        return id > 0;
    }

    public static boolean validTitle(String title) {
        return title.length() > 2;
    }

    public static boolean validPrice(double price) {
        return price > 0;
    }

    public static boolean validDesc(String s) {
        return s.length() > 8;
    }
}
