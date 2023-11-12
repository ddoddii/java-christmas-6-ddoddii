package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Parser;
import christmas.validator.MenuValidator;

public class InputView {

    public static void printGreetingMessage() {
        System.out.println(ViewMessage.GREETING.getMessage());
    }


    public static  int readDate() {
        System.out.println(ViewMessage.DATE.getMessage());

        String input = Console.readLine();
        return Parser.parseDate(input);
    }


    public static String readMenu() {
        System.out.println(ViewMessage.MENU.getMessage());
        String input = Console.readLine();
        MenuValidator.validateMenuInputFormat(input);
        return input;
    }

}
