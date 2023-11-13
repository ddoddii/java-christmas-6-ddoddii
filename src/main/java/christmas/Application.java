package christmas;

import christmas.controller.PromotionController;
import christmas.model.strategy.BadgeStrategy;
import christmas.model.strategy.DiscountStrategy;
import christmas.model.strategy.WootecoBadgeStrategy;
import christmas.model.strategy.WootecoDiscountStrategy;
import christmas.service.PromotionService;

public class Application {
    public static void main(String[] args) {
        DiscountStrategy discountStrategy = new WootecoDiscountStrategy();
        BadgeStrategy badgeStrategy = new WootecoBadgeStrategy();
        PromotionController promotionController = new PromotionController(
                new PromotionService(discountStrategy, badgeStrategy)
        );

        promotionController.run();

    }
}
