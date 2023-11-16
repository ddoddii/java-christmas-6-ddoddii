package christmas.model.strategy;


import christmas.model.Date;
import christmas.model.MenuCount;

public interface DiscountStrategy {

    int christmasDiscount(Date date);
    int weekendDiscount(MenuCount menuCount, Date date);
    int weekdayDiscount(MenuCount menuCount, Date date);
    int specialDayDiscount(Date date);
    int giftEventDiscount(MenuCount menuCount);


}
