package christmas.model;

import christmas.constant.DateConstant;
import christmas.exception.date.DateRangeException;

public class Date {
    private final int MIN_DATE = DateConstant.MIN_DATE.getDate();
    private final int MAX_DATE = DateConstant.MAX_DATE.getDate();
    private final int date;

    private Date(int date) {
        validateDateRange(date);
        this.date = date;
    }

    public static Date of(int date) {
        return new Date(date);
    }

    public int getValue() {
        return date;
    }

    private void validateDateRange(int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new DateRangeException();
        }
    }

    public boolean isWeekday() {
        return DateConstant.isWeekday(date);
    }

    public boolean isWeekend() {
        return DateConstant.isWeekend(date);
    }

    public boolean isBeforeXmas() {
        return DateConstant.isBeforeXmas(date);
    }

    public boolean isSpecialDate() {
        return DateConstant.isSpecialDay(date);
    }

}
