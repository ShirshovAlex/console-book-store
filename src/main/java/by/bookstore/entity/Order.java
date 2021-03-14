package by.bookstore.entity;

import java.util.Arrays;
import java.util.Objects;

public class Order {
    private static int incId = 1;
    private int id = incId++;

    private boolean isDelivery;

    private Store store;

    private Address address;

    private Book[] books;

    private User user;

    private Status status = Status.ACTIVE;

    public Order() {
    }

    public Order(Store store, Book[] books, User user) {
        this.store = store;
        this.books = books;
        this.user = user;
    }

    public Order(Address address, Book[] books, User user) {
        this.address = address;
        this.books = books;
        this.user = user;
        this.isDelivery = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Arrays.equals(books, order.books) &&
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(user);
        result = 31 * result + Arrays.hashCode(books);
        return result;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", isDelivery=" + isDelivery +
                ", store=" + store +
                ", address=" + address +
                ", books=" + Arrays.toString(books) +
                ", user=" + user +
                ", status=" + status +
                '}';
    }
}
