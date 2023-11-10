package christmas.model;

import christmas.exception.date.DateRangeException;

public class Date {
    private final int MIN_DATE = 1;
    private final int MAX_DATE = 31;
    private final int date;

    public Date(int date) {
        validateDateRange(date);
        this.date = date;
    }

    private void validateDateRange(int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new DateRangeException();
        }
    }

}
