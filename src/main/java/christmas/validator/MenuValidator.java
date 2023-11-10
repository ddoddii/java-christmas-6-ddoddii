package christmas.validator;

import christmas.exception.menu.DuplicateMenuException;
import christmas.exception.menu.MenuCountZeroException;
import christmas.exception.menu.MenuFormatException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuValidator {

    public static void validateMenuInput(String input){
        validateMenuFormat(input);
        validateMenuAmount(input);
        validateDuplicateMenu(input);
    }

    /*
    메뉴 형식 {메뉴이름}-{메뉴수량} 에 맞지 않을 시 예외 발생
     */
    private static void validateMenuFormat(String input){
        String pattern = "([가-힣a-zA-Z]+-\\d+)(,[가-힣a-zA-Z]+-\\d+)*";

        if (!input.matches(pattern)) {
            throw new MenuFormatException();
        }
    }

    private static void validateDuplicateMenu(String input){
        Set<String> menuNames = Arrays.stream(input.split(","))
                .map(item -> item.split("-")[0])
                .collect(Collectors.toSet());
        if (menuNames.size() != input.split(",").length){
            throw new DuplicateMenuException();
        }
    }

    private static void validateMenuAmount(String input){
        Set<String> menuCounts = Arrays.stream(input.split(","))
                .map(item -> item.split("-")[1])
                .collect(Collectors.toSet());
        if (menuCounts.contains("0")){
            throw new MenuCountZeroException();
        }
    }
}
