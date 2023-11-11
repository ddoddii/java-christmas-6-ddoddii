package christmas.exception.menu;

import christmas.exception.message.ExceptionMessage;

public class OnlyDrinkException extends IllegalArgumentException {
    public OnlyDrinkException() {
        super(ExceptionMessage.WRONG_MENU_FORMAT.getMessage());
    }
}
