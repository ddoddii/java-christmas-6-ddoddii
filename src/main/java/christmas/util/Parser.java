package christmas.util;

import static christmas.exception.CustomException.DATE_NOT_INTEGER_EXCEPTION;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {
    public static int parseDate(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw DATE_NOT_INTEGER_EXCEPTION;
        }
    }

    /*
    input 으로 들어온 메뉴 파싱
     */
    public static Map<String, Integer> parseMenuCount(String input) {
        return Arrays.stream(input.split(","))
                .map(item -> item.split("-"))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> Integer.parseInt(parts[1])
                ));
    }

}
