package christmas.service;

import christmas.model.Date;
import christmas.model.Discount;
import christmas.model.Menu;
import christmas.model.MenuCount;

public class DiscountService {

    public boolean canGetDiscount(MenuCount menuCount) {
        return menuCount.calculateTotalAmount() >= Discount.MIN_DISCOUNT_SERVICE.getAmount();
    }

    public int calculatePromotionAmount(MenuCount menuCount, Date date) {
        if (canGetDiscount(menuCount)) {
            return calculateTotalDiscountAmount(menuCount, date)
                    + giftEventDiscount(menuCount);
        }
        return Discount.NO_DISCOUNT.getAmount();
    }

    public int calculateTotalDiscountAmount(MenuCount menuCount, Date date) {
        if (canGetDiscount(menuCount)) {
            return christmasDiscount(date)
                    + weekdayDiscount(menuCount, date)
                    + specialDayDiscount(date)
                    + weekendDiscount(menuCount, date);
        }
        return Discount.NO_DISCOUNT.getAmount();
    }

    public int christmasDiscount(Date date) {
        if (date.isBeforeXmas()) {
            return (Discount.CHRISTMAS_START_DISCOUNT.getAmount() + (date.getValue() - 1)
                    * Discount.DISCOUNT_INCREMENT.getAmount());
        }
        return Discount.NO_DISCOUNT.getAmount();
    }

    public int weekendDiscount(MenuCount menuCount, Date date) {
        if (date.isWeekend()) {
            return calculateWeekendDiscountAmount(menuCount);
        }
        return Discount.NO_DISCOUNT.getAmount();
    }

    public int weekdayDiscount(MenuCount menuCount, Date date) {
        if (date.isWeekday()) {
            return calculateWeekdayDiscountAmount(menuCount);
        }
        return Discount.NO_DISCOUNT.getAmount();
    }

    public int specialDayDiscount(Date date) {
        if (date.isSpecialDate()) {
            return Discount.SPECIAL_DAY_DISCOUNT.getAmount();
        }
        return Discount.NO_DISCOUNT.getAmount();
    }

    public int giftEventDiscount(MenuCount menuCount) {
        if (menuCount.calculateTotalAmount() >= Discount.GIFT_EVENT_THRESHOLD.getAmount()) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return Discount.NO_DISCOUNT.getAmount();
    }


    private int calculateWeekendDiscountAmount(MenuCount menuCount) {
        return menuCount.getValue()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isMainCategory())
                .mapToInt(entry -> Discount.FIXED_DISCOUNT.getAmount() * entry.getValue())
                .sum();
    }

    private int calculateWeekdayDiscountAmount(MenuCount menuCount) {
        return menuCount.getValue()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isDessertCategory())
                .mapToInt(entry -> Discount.FIXED_DISCOUNT.getAmount() * entry.getValue())
                .sum();
    }


}
