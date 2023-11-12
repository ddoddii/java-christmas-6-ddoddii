package christmas.model.strategy;

import static christmas.model.constant.Discount.CHRISTMAS_START_DISCOUNT;
import static christmas.model.constant.Discount.DISCOUNT_INCREMENT;
import static christmas.model.constant.Discount.FIXED_DISCOUNT;
import static christmas.model.constant.Discount.GIFT_EVENT_THRESHOLD;
import static christmas.model.constant.Discount.MIN_DISCOUNT_SERVICE;
import static christmas.model.constant.Discount.NO_DISCOUNT;
import static christmas.model.constant.Discount.SPECIAL_DAY_DISCOUNT;

import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.MenuCount;
import christmas.model.Promotion;
import java.util.EnumMap;

public class WootecoDiscountStrategy implements DiscountStrategy{


    @Override
    public boolean canGetDiscount(MenuCount menuCount) {
        return menuCount.calculateTotalAmount() >= MIN_DISCOUNT_SERVICE.getAmount();
    }

    @Override
    public int calculatePromotionAmount(MenuCount menuCount, Date date) {
        if (canGetDiscount(menuCount)) {
            return calaulateTotalDiscountAmount(menuCount, date)
                    + giftEventDiscount(menuCount);
        }
        return NO_DISCOUNT.getAmount();
    }

    @Override
    public int calaulateTotalDiscountAmount(MenuCount menuCount, Date date) {
        if (canGetDiscount(menuCount)) {
            return christmasDiscount(date)
                    + weekdayDiscount(menuCount, date)
                    + specialDayDiscount(date)
                    + weekendDiscount(menuCount, date);
        }
        return NO_DISCOUNT.getAmount();
    }
    @Override
    public EnumMap<Promotion, Integer> getPromotionStatus(MenuCount menuCount, Date date){
        EnumMap<Promotion,Integer> promotionStatus = new EnumMap<>(Promotion.class);
        promotionStatus.put(Promotion.CHRISTMAS_DISCOUNT, christmasDiscount(date));
        promotionStatus.put(Promotion.WEEKDAY_DISCOUNT, weekdayDiscount(menuCount, date));
        promotionStatus.put(Promotion.WEEKEND_DISCOUNT, weekendDiscount(menuCount, date));
        promotionStatus.put(Promotion.SPECIAL_DISCOUNT, specialDayDiscount(date));
        promotionStatus.put(Promotion.GIFT_EVENT, giftEventDiscount(menuCount));
        return promotionStatus;
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
