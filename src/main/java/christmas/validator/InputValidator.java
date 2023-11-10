package christmas.validator;

import christmas.exception.menu.MenuFormatException;
import java.awt.Menu;

public class InputValidator {
    public static void validateMenuFormat(String input){
        String pattern = "([가-힣a-zA-Z]+-\\d+)(,[가-힣a-zA-Z]+-\\d+)*";

        if (!input.matches(pattern)) {
            throw new MenuFormatException();
        }
    }
}
