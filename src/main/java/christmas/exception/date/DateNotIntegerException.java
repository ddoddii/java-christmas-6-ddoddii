package christmas.exception.date;

import christmas.exception.message.ExceptionMessage;

public class DateNotIntegerException extends IllegalArgumentException{
    public DateNotIntegerException() {
        super(ExceptionMessage.DATE_NOT_INT.getMessage());
    }
}
