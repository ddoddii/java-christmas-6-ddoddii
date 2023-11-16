package christmas.model.constant;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum DateConstant {
    MIN_DATE(1),
    MAX_DATE(31),
    XMAS(25);

    public static final Set<Integer> WEEKDAYS = new HashSet<>();
    public static final Set<Integer> WEEKENDS = new HashSet<>();
    public static final Set<Integer> SPECIAL_DAYS = new HashSet<>(Arrays.asList(
            3, 10, 17, 24, 25, 31
    ));

    static {
        LocalDate start = LocalDate.of(2023, 12, MIN_DATE.date);
        LocalDate end = LocalDate.of(2023, 12, MAX_DATE.date);

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            DayOfWeek day = date.getDayOfWeek();
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.FRIDAY) {
                WEEKENDS.add(date.getDayOfMonth());
                continue;
            }
            WEEKDAYS.add(date.getDayOfMonth());
        }
    }

    private final int date;

    DateConstant(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

}
