package christmas.exception.menu;

import christmas.exception.message.ExceptionMessage;

public class DuplicateMenuException extends IllegalArgumentException{
    public DuplicateMenuException() {
        super(ExceptionMessage.WRONG_MENU_FORMAT.getMessage());
    }
}
