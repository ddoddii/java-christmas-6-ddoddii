package christmas.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum DateConstant {
    MIN_DATE(1),
    MAX_DATE(31),
    XMAS(25);

    private int date;
    private static final Set<Integer> WEEKDAYS = new HashSet<>(Arrays.asList(
            1, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 18, 19, 20, 21, 22, 25, 26, 27, 28, 29
    ));
    private static final Set<Integer> WEEKENDS = new HashSet<>(Arrays.asList(
            1,2,8,9,15,16,22,23,29,30
    ));
    DateConstant(int date){
        this.date = date;
    }

    public int getDate(){
        return date;
    }

    public static boolean isWeekday(int date){
        return WEEKDAYS.contains(date);
    }

    public static boolean isWeekend(int date){
        return WEEKENDS.contains(date);
    }

    public static boolean isBeforeXmas(int date){
        return XMAS.date >= date;
    }
}
