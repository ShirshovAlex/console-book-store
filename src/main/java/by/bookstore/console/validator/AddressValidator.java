package by.bookstore.console.validator;

import by.bookstore.entity.Address;

public class AddressValidator {
    public static boolean validAddress(Address address) {
        return address.getStreet().length() > 2;
    }
}
