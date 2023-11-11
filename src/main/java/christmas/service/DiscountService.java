package christmas.service;

import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.MenuCount;

public class DiscountService {
    private final int START_XMAS_DISCOUNT_AMOUNT = 1000;
    private final int INCREASING_AMOUNT = 100;
    private final int MIN_DISCOUNT_SERVICE_AMOUNT = 10000;
    private final int DISCOUNT_AMOUNT = 2023;
    private final int SPECIAL_DAY_DISCOUNT = 1000;
    private final int GIFT_EVENT_MIN_AMOUNT = 120000;

    public boolean canGetDiscount(MenuCount menuCount){
        return menuCount.calculateTotalAmount() >= MIN_DISCOUNT_SERVICE_AMOUNT;
    }

    public int calculatePromotionAmount(MenuCount menuCount, Date date){
        return calculateTotalDiscountAmount(menuCount,date)
                + giftEventDiscount(menuCount);
    }

    public int calculateTotalDiscountAmount(MenuCount menuCount, Date date){
        return christmasDiscount(date)
                + weekdayDiscount(menuCount,date)
                + specialDayDiscount(date)
                + weekendDiscount(menuCount, date);
    }

    public int christmasDiscount(Date date) {
        if (date.isBeforeXmas()) {
            return (START_XMAS_DISCOUNT_AMOUNT + (date.getValue() - 1) * INCREASING_AMOUNT);
        }
        return 0;
    }

    public int weekendDiscount(MenuCount menuCount, Date date){
        if (date.isWeekend()){
            return  calculateWeekendDiscountAmount(menuCount);
        }
        return 0;
    }

    public int  weekdayDiscount(MenuCount menuCount, Date date){
        if (date.isWeekday()){
            return calculateWeekdayDiscountAmount(menuCount);
        }
        return 0;
    }

    public int specialDayDiscount(Date date){
        if (date.isSpecialDate()){
            return SPECIAL_DAY_DISCOUNT;
        }
        return 0;
    }

    public int giftEventDiscount(MenuCount menuCount){
        if (menuCount.calculateTotalAmount() >= GIFT_EVENT_MIN_AMOUNT){
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }


    private int calculateWeekendDiscountAmount(MenuCount menuCount){
        return menuCount.getValue()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isMainCategory())
                .mapToInt(entry -> DISCOUNT_AMOUNT * entry.getValue())
                .sum();
    }

    private int calculateWeekdayDiscountAmount(MenuCount menuCount){
        return menuCount.getValue()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isDessertCategory())
                .mapToInt(entry -> DISCOUNT_AMOUNT * entry.getValue())
                .sum();
    }


}
