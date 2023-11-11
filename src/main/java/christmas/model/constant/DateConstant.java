package christmas.model.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum DateConstant {
    MIN_DATE(1),
    MAX_DATE(31),
    XMAS(25);

    private int date;
    private static final Set<Integer> WEEKDAYS = new HashSet<>(Arrays.asList(
            3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31
    ));
    private static final Set<Integer> WEEKENDS = new HashSet<>(Arrays.asList(
            1, 2, 8, 9, 15, 16, 22, 23, 29, 30
    ));

    private static final Set<Integer> SPECIAL_DAYS = new HashSet<>(Arrays.asList(
            3, 10, 17, 24, 31
    ));

    DateConstant(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public static boolean isWeekday(int date) {
        return WEEKDAYS.contains(date);
    }

    public static boolean isWeekend(int date) {
        return WEEKENDS.contains(date);
    }

    public static boolean isBeforeXmas(int date) {
        return XMAS.date >= date;
    }

    public static boolean isSpecialDay(int date) {
        return SPECIAL_DAYS.contains(date);
    }
}
