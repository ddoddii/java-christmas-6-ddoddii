package christmas.exception.menu;

import christmas.exception.message.ExceptionMessage;

public class MenuAmoutOverLimitException extends IllegalArgumentException {
    public MenuAmoutOverLimitException() {
        super(ExceptionMessage.AMOUNT_OVER_LIMIT.getMessage());
    }
}

