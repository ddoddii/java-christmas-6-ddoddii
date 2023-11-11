package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Parser;
import christmas.validator.MenuValidator;
import java.sql.SQLOutput;

public class InputView {
    private final String DATE_EVENT_MESSAGE = "12월 %d에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public void printGreetingMessage() {
        System.out.println(ViewMessage.GREETING.getMessage());
    }

    public int readDate() {
        System.out.println(ViewMessage.DATE.getMessage());
        String input = Console.readLine();
        return Parser.parseDate(input);
    }

    public String readMenuCount() {
        System.out.println(ViewMessage.MENU.getMessage());
        String input = Console.readLine();
        MenuValidator.validateMenuInputFormat(input);
        return input;
    }



}
