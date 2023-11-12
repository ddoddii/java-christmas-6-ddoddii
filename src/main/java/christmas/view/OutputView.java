package christmas.view;

import static christmas.view.ViewMessage.AMOUNT_BEFORE_DISCOUNT;
import static christmas.view.ViewMessage.GIFT_EVENT;
import static christmas.view.ViewMessage.ORDERED_MENU;
import static christmas.view.ViewMessage.PROMOTION;
import static christmas.view.ViewMessage.TOTAL_PROMOTION_AMOUNT;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private final String QUANTITY_SUFFIX = "개";
    private final String MONEY_SUFFIX = "원";
    private final String CHAMPAIGN_GIFT = "샴페인 1개";
    private final String NOTHING = "없음";
    private final String STATUS_DELIMITER = ": ";
    private final String MINUS = "-";

    public void displayEventMessage(int date) {
        String formattedMessage = String.format(ViewMessage.EVENT.getMessage(), date);
        System.out.println(formattedMessage);
    }

    public void displayOrderedMenu(Map<String, Integer> parsedMenu) {
        String orders = parsedMenu.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + QUANTITY_SUFFIX)
                .collect(Collectors.joining("\n"));
        System.out.println(ORDERED_MENU.getMessage());
        System.out.println(orders);
    }

    public void displayTotalOrderAmount(int amount) {
        String formattedAmount = formatMoney(amount);
        System.out.println(AMOUNT_BEFORE_DISCOUNT.getMessage());
        System.out.println(formattedAmount + MONEY_SUFFIX);
    }

    public void displayGiftEvent(boolean canGetGift) {
        System.out.println(GIFT_EVENT.getMessage());
        if (canGetGift) {
            System.out.println(CHAMPAIGN_GIFT);
            return;
        }
        System.out.println(NOTHING);

    }

    public void displayPromotionStatus(Map<String, Integer> promotionStatus) {
        String promotion = determinePromotionStatus(promotionStatus);
        System.out.println(PROMOTION.getMessage());
        System.out.println(promotion);
    }

    public void displayPromotionAmount(int promotionAmount){
        String formattedPromotionAmount = determinePromotionAmount(promotionAmount);
        System.out.println(TOTAL_PROMOTION_AMOUNT.getMessage());
        System.out.println(formattedPromotionAmount);
    }

    private String formatMoney(int amount) {
        return String.format(Locale.US, "%,d", amount);
    }

    private String determinePromotionStatus(Map<String, Integer> promotionStatus) {
        if (promotionStatus != null) {
            return promotionStatus.entrySet().stream()
                    .map(entry -> entry.getKey() + STATUS_DELIMITER + MINUS
                            + formatMoney(entry.getValue()) + MONEY_SUFFIX)
                    .collect(Collectors.joining("\n"));
        }
        return NOTHING;
    }

    private String determinePromotionAmount(int promotionAmount){
        if (promotionAmount == 0){
            return (promotionAmount + MONEY_SUFFIX);
        }
        return (MINUS + formatMoney(promotionAmount) + MONEY_SUFFIX);
    }
}
