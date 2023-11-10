package christmas.exception.menu;

import christmas.exception.message.ExceptionMessage;

public class MenuCountZeroException extends IllegalArgumentException{
    public MenuCountZeroException() {
        super(ExceptionMessage.WRONG_MENU_FORMAT.getMessage());
    }
}
