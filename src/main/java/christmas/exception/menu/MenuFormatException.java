package christmas.exception.menu;

import christmas.exception.message.ExceptionMessage;

public class MenuFormatException extends IllegalArgumentException {
    public MenuFormatException() {
        super(ExceptionMessage.WRONG_MENU_FORMAT.getMessage());
    }
}

