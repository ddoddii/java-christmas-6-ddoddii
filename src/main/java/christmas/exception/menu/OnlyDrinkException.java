package christmas.exception.menu;

import christmas.exception.message.ExceptionMessage;

public class OnlyDrinkException extends IllegalArgumentException {
    public OnlyDrinkException() {
        super(ExceptionMessage.ONLY_DRINK.getMessage());
    }
}
