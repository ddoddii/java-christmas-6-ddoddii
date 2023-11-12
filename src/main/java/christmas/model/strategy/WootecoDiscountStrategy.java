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

    private final MenuCount menuCount;
    private final Date date;

    public WootecoDiscountStrategy(MenuCount menuCount, Date date){
        this.menuCount = menuCount;
        this.date = date;
    }


    @Override
    public boolean canGetDiscount() {
        return menuCount.calculateTotalAmount() >= MIN_DISCOUNT_SERVICE.getAmount();
    }

    @Override
    public int calculatePromotionAmount() {
        if (canGetDiscount()) {
            return calaulateTotalDiscountAmount()
                    + giftEventDiscount();
        }
        return NO_DISCOUNT.getAmount();
    }

    @Override
    public int calaulateTotalDiscountAmount() {
        if (canGetDiscount()) {
            return christmasDiscount()
                    + weekdayDiscount()
                    + specialDayDiscount()
                    + weekendDiscount();
        }
        return NO_DISCOUNT.getAmount();
    }
    @Override
    public EnumMap<Promotion, Integer> getPromotionStatus(){
        EnumMap<Promotion,Integer> promotionStatus = new EnumMap<>(Promotion.class);
        promotionStatus.put(Promotion.CHRISTMAS_DISCOUNT, christmasDiscount());
        promotionStatus.put(Promotion.WEEKDAY_DISCOUNT, weekdayDiscount());
        promotionStatus.put(Promotion.WEEKEND_DISCOUNT, weekendDiscount());
        promotionStatus.put(Promotion.SPECIAL_DISCOUNT, specialDayDiscount());
        promotionStatus.put(Promotion.GIFT_EVENT, giftEventDiscount());
        return promotionStatus;
    }

    public int christmasDiscount() {
        if (date.isBeforeXmas()) {
            return (CHRISTMAS_START_DISCOUNT.getAmount() + (date.getValue() - 1)
                    * DISCOUNT_INCREMENT.getAmount());
        }
        return NO_DISCOUNT.getAmount();
    }

    public int weekendDiscount() {
        if (date.isWeekend()) {
            return calculateWeekendDiscountAmount();
        }
        return NO_DISCOUNT.getAmount();
    }

    public int weekdayDiscount() {
        if (date.isWeekday()) {
            return calculateWeekdayDiscountAmount();
        }
        return NO_DISCOUNT.getAmount();
    }

    public int specialDayDiscount() {
        if (date.isSpecialDate()) {
            return SPECIAL_DAY_DISCOUNT.getAmount();
        }
        return NO_DISCOUNT.getAmount();
    }

    public int giftEventDiscount() {
        if (menuCount.calculateTotalAmount() >= GIFT_EVENT_THRESHOLD.getAmount()) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return NO_DISCOUNT.getAmount();
    }


    private int calculateWeekendDiscountAmount() {
        return menuCount.getValue()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isMainCategory())
                .mapToInt(entry -> FIXED_DISCOUNT.getAmount() * entry.getValue())
                .sum();
    }

    private int calculateWeekdayDiscountAmount() {
        return menuCount.getValue()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isDessertCategory())
                .mapToInt(entry -> FIXED_DISCOUNT.getAmount() * entry.getValue())
                .sum();
    }
}
