package christmas.exception.menu;

import christmas.exception.message.ExceptionMessage;

public class MenuNotFoundException extends IllegalArgumentException {
    public MenuNotFoundException() {
        super(ExceptionMessage.WRONG_MENU_FORMAT.getMessage());
    }
}
