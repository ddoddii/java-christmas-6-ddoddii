package christmas.model.strategy;


import christmas.model.Promotion;
import java.util.EnumMap;

public interface DiscountStrategy {

    boolean canGetDiscount();

    int calculatePromotionAmount();

    int calaulateTotalDiscountAmount();

    EnumMap<Promotion, Integer> getPromotionStatus();


}
