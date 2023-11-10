package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Parser;

public class InputView {
    private final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    private static final String DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final String MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public void printGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public static int readDate() {
        System.out.println(DATE_MESSAGE);
        String input = Console.readLine();
        return Parser.parseDate(input);
    }

}
