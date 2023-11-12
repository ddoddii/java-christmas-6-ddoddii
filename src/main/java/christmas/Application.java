package christmas;

import christmas.controller.PromotionController;
import christmas.service.PromotionService;

public class Application {
    public static void main(String[] args) {
        PromotionController promotionController = new PromotionController(
                new PromotionService()
        );

        promotionController.run();

    }
}
