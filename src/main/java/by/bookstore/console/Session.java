package by.bookstore.console;

import by.bookstore.entity.Basket;
import by.bookstore.entity.User;

import java.util.Date;

public class Session {
    private static int incId = 1;
    private int id = incId++;

    private Date createSession = new Date();

    private User currentUser;
    private Basket basket;

    public Session() {
    }

    public Session(User currentUser, Basket basket) {
        this.currentUser = currentUser;
        this.basket = basket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateSession() {
        return createSession;
    }

    public void setCreateSession(Date createSession) {
        this.createSession = createSession;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
