package by.bookstore.entity;

import java.util.Calendar;

public abstract class AbstractAudit {
    private final Calendar createDate = Calendar.getInstance();

    public Calendar getCreateDate() {
        return createDate;
    }
}
