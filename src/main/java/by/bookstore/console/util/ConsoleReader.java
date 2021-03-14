package by.bookstore.console.util;

import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class ConsoleReader implements Reader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readString() {
        return scanner.next();
    }

    @Override
    public int readInt() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return -1;
    }


    @Override
    public double readDouble() {
        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        }
        return -1;
    }
}
