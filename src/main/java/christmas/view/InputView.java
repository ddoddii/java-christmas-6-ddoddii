package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Parser;
import christmas.validator.MenuValidator;

public class InputView {

    public void printGreetingMessage() {
        System.out.println(ViewMessage.GREETING.getMessage());
    }

    public int readDate() {
        System.out.println(ViewMessage.DATE.getMessage());
        String input = Console.readLine();
        return Parser.parseDate(input);
    }

    public String readMenu() {
        System.out.println(ViewMessage.MENU.getMessage());
        String input = Console.readLine();
        MenuValidator.validateMenuInputFormat(input);
        return input;
    }


}
