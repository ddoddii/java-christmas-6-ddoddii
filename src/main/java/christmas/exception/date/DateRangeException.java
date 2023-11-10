package christmas.exception.date;

import christmas.exception.message.ExceptionMessage;

public class DateRangeException extends IllegalArgumentException{
    public DateRangeException() {
        super(ExceptionMessage.DATE_OUT_OF_RANGE.getMessage());
    }
}
