package christmas.model.strategy;

import christmas.model.Date;
import christmas.model.MenuCount;

public interface DiscountStrategy {
    boolean canGetDiscount(MenuCount menuCount);
    int calculatePromotionAmount(MenuCount menuCount, Date date);
    int calaulateTotalDiscountAmount(MenuCount menuCount, Date date);
}
