package by.bookstore.console.util;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter implements Writer {
    @Override
    public void write(int a) {
        System.out.println("system> " + a);
    }

    @Override
    public void write(double a) {
        System.out.println("system> " + a);
    }

    @Override
    public void write(String a) {
        System.out.println("system> " + a);
    }

    @Override
    public void write(Object[] array) {
        for (Object o : array) {
            System.out.println("system> " + o);
        }
    }
}
