package christmas.service;

import static christmas.model.constant.DiscountConstant.MIN_DISCOUNT_SERVICE;
import static christmas.model.constant.DiscountConstant.NO_DISCOUNT;
import static christmas.model.constant.PromotionConstant.CHRISTMAS_DISCOUNT;
import static christmas.model.constant.PromotionConstant.GIFT_EVENT;
import static christmas.model.constant.PromotionConstant.SPECIAL_DISCOUNT;
import static christmas.model.constant.PromotionConstant.WEEKDAY_DISCOUNT;
import static christmas.model.constant.PromotionConstant.WEEKEND_DISCOUNT;

import christmas.model.Date;
import christmas.model.constant.EventBadge;
import christmas.model.MenuCount;
import christmas.model.constant.PromotionConstant;
import christmas.model.strategy.BadgeStrategy;
import christmas.model.strategy.DiscountStrategy;
import java.util.EnumMap;

public class PromotionService {
    private final DiscountStrategy discountStrategy;
    private final BadgeStrategy badgeStrategy;


    public PromotionService(DiscountStrategy discountStrategy, BadgeStrategy badgeStrategy){
            this.discountStrategy = discountStrategy;
        this.badgeStrategy = badgeStrategy;

    }

    public boolean canGetDiscount(MenuCount menuCount) {
        return menuCount.calculateTotalAmount() >= MIN_DISCOUNT_SERVICE.getAmount();
    }

    public int calculatePromotionAmount(MenuCount menuCount, Date date) {
        if (canGetDiscount(menuCount)) {
            return calaulateTotalDiscountAmount(menuCount, date)
                    + discountStrategy.giftEventDiscount(menuCount);
        }
        return NO_DISCOUNT.getAmount();
    }

    public int calaulateTotalDiscountAmount(MenuCount menuCount, Date date) {
        if (canGetDiscount(menuCount)) {
            return discountStrategy.christmasDiscount(date)
                    + discountStrategy.weekdayDiscount(menuCount, date)
                    + discountStrategy.specialDayDiscount(date)
                    + discountStrategy.weekendDiscount(menuCount, date);
        }
        return NO_DISCOUNT.getAmount();
    }
    public EnumMap<PromotionConstant, Integer> getPromotionStatus(MenuCount menuCount, Date date){
        EnumMap<PromotionConstant,Integer> promotionStatus = new EnumMap<>(PromotionConstant.class);
        promotionStatus.put(CHRISTMAS_DISCOUNT, discountStrategy.christmasDiscount(date));
        promotionStatus.put(WEEKDAY_DISCOUNT, discountStrategy.weekdayDiscount(menuCount, date));
        promotionStatus.put(WEEKEND_DISCOUNT, discountStrategy.weekendDiscount(menuCount, date));
        promotionStatus.put(SPECIAL_DISCOUNT, discountStrategy.specialDayDiscount(date));
        promotionStatus.put(GIFT_EVENT, discountStrategy.giftEventDiscount(menuCount));
        return promotionStatus;
    }

    public String calculateBadgeStatus(MenuCount menuCount, Date date){
        int promotionAmount = calculatePromotionAmount(menuCount, date);
        EventBadge badge = badgeStrategy.calculateBadgeGrade(promotionAmount);
        return badge.getName();
    }

}
