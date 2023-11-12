package christmas.model.strategy;

import christmas.model.Date;
import christmas.model.MenuCount;
import christmas.model.Promotion;
import java.util.EnumMap;

public interface DiscountStrategy {
    boolean canGetDiscount(MenuCount menuCount);

    int calculatePromotionAmount(MenuCount menuCount, Date date);

    int calaulateTotalDiscountAmount(MenuCount menuCount, Date date);

    EnumMap<Promotion, Integer> getPromotionStatus(MenuCount menuCount, Date date);


}
