package christmas.service;

import static christmas.model.Discount.CHRISTMAS_START_DISCOUNT;
import static christmas.model.Discount.DISCOUNT_INCREMENT;
import static christmas.model.Discount.FIXED_DISCOUNT;
import static christmas.model.Discount.GIFT_EVENT_THRESHOLD;
import static christmas.model.Discount.MIN_DISCOUNT_SERVICE;
import static christmas.model.Discount.NO_DISCOUNT;
import static christmas.model.Discount.SPECIAL_DAY_DISCOUNT;

import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.MenuCount;

public class DiscountService {

    public boolean canGetDiscount(MenuCount menuCount) {
        return menuCount.calculateTotalAmount() >= MIN_DISCOUNT_SERVICE.getAmount();
    }

    public int calculatePromotionAmount(MenuCount menuCount, Date date) {
        if (canGetDiscount(menuCount)) {
            return calculateTotalDiscountAmount(menuCount, date)
                    + giftEventDiscount(menuCount);
        }
        return NO_DISCOUNT.getAmount();
    }

    public int calculateTotalDiscountAmount(MenuCount menuCount, Date date) {
        if (canGetDiscount(menuCount)) {
            return christmasDiscount(date)
                    + weekdayDiscount(menuCount, date)
                    + specialDayDiscount(date)
                    + weekendDiscount(menuCount, date);
        }
        return NO_DISCOUNT.getAmount();
    }

    public int christmasDiscount(Date date) {
        if (date.isBeforeXmas()) {
            return (CHRISTMAS_START_DISCOUNT.getAmount() + (date.getValue() - 1)
                    * DISCOUNT_INCREMENT.getAmount());
        }
        return NO_DISCOUNT.getAmount();
    }

    public int weekendDiscount(MenuCount menuCount, Date date) {
        if (date.isWeekend()) {
            return calculateWeekendDiscountAmount(menuCount);
        }
        return NO_DISCOUNT.getAmount();
    }

    public int weekdayDiscount(MenuCount menuCount, Date date) {
        if (date.isWeekday()) {
            return calculateWeekdayDiscountAmount(menuCount);
        }
        return NO_DISCOUNT.getAmount();
    }

    public int specialDayDiscount(Date date) {
        if (date.isSpecialDate()) {
            return SPECIAL_DAY_DISCOUNT.getAmount();
        }
        return NO_DISCOUNT.getAmount();
    }

    public int giftEventDiscount(MenuCount menuCount) {
        if (menuCount.calculateTotalAmount() >= GIFT_EVENT_THRESHOLD.getAmount()) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return NO_DISCOUNT.getAmount();
    }


    private int calculateWeekendDiscountAmount(MenuCount menuCount) {
        return menuCount.getValue()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isMainCategory())
                .mapToInt(entry -> FIXED_DISCOUNT.getAmount() * entry.getValue())
                .sum();
    }

    private int calculateWeekdayDiscountAmount(MenuCount menuCount) {
        return menuCount.getValue()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isDessertCategory())
                .mapToInt(entry -> FIXED_DISCOUNT.getAmount() * entry.getValue())
                .sum();
    }


}
