package christmas.model;

import static christmas.model.constant.DateConstant.SPECIAL_DAYS;
import static christmas.model.constant.DateConstant.WEEKDAYS;
import static christmas.model.constant.DateConstant.WEEKENDS;
import static christmas.model.constant.DateConstant.XMAS;

import christmas.model.constant.DateConstant;
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
        return WEEKDAYS.contains(date);
    }

    public boolean isWeekend() {
        return WEEKENDS.contains(date);
    }

    public boolean isBeforeXmas() {
        return XMAS.getDate() >= date;
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(date);
    }


}
