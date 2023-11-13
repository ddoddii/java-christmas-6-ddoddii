package christmas.model.strategy;

import static christmas.model.constant.DiscountConstant.CHRISTMAS_START_DISCOUNT;
import static christmas.model.constant.DiscountConstant.DISCOUNT_INCREMENT;
import static christmas.model.constant.DiscountConstant.FIXED_DISCOUNT;
import static christmas.model.constant.DiscountConstant.GIFT_EVENT_THRESHOLD;
import static christmas.model.constant.DiscountConstant.NO_DISCOUNT;
import static christmas.model.constant.DiscountConstant.SPECIAL_DAY_DISCOUNT;

import christmas.model.Date;
import christmas.model.constant.Menu;
import christmas.model.MenuCount;

public class WootecoDiscountStrategy implements DiscountStrategy {

    @Override
    public int christmasDiscount(Date date) {
        if (date.isBeforeXmas()) {
            return (CHRISTMAS_START_DISCOUNT.getAmount() + (date.getValue() - 1)
                    * DISCOUNT_INCREMENT.getAmount());
        }
        return NO_DISCOUNT.getAmount();
    }

    @Override
    public int weekendDiscount(MenuCount menuCount, Date date) {
        if (date.isWeekend()) {
            return calculateWeekendDiscountAmount(menuCount);
        }
        return NO_DISCOUNT.getAmount();
    }

    @Override
    public int weekdayDiscount(MenuCount menuCount, Date date) {
        if (date.isWeekday()) {
            return calculateWeekdayDiscountAmount(menuCount);
        }
        return NO_DISCOUNT.getAmount();
    }

    @Override
    public int specialDayDiscount(Date date) {
        if (date.isSpecialDay()) {
            return SPECIAL_DAY_DISCOUNT.getAmount();
        }
        return NO_DISCOUNT.getAmount();
    }

    @Override
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
