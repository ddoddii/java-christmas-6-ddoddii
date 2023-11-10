package christmas.util;

import christmas.exception.date.DateNotIntegerException;
import christmas.exception.date.DateRangeException;

public class Parser {
    public static int parseDate(String input){
        try{
            return Integer.parseInt(input);
        } catch (NumberFormatException e){
            throw new DateNotIntegerException();
        }
    }
}
