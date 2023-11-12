package christmas.view;

import static christmas.view.ViewMessage.AMOUNT_BEFORE_DISCOUNT;
import static christmas.view.ViewMessage.CHAMPAIGN_GIFT;
import static christmas.view.ViewMessage.EVENT_BADGE;
import static christmas.view.ViewMessage.EXPECTED_AMOUNT;
import static christmas.view.ViewMessage.GIFT_EVENT;
import static christmas.view.ViewMessage.NO_PROMOTION;
import static christmas.view.ViewMessage.ORDERED_MENU;
import static christmas.view.ViewMessage.PROMOTION;
import static christmas.view.ViewMessage.TOTAL_PROMOTION_AMOUNT;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private final String QUANTITY_SUFFIX = "개";
    private final String MONEY_SUFFIX = "원";
    private final String STATUS_DELIMITER = ": ";
    private final String MINUS = "-";

    public void printEventMessage(int date) {
        String formattedMessage = String.format(ViewMessage.EVENT.getMessage(), date);
        System.out.println(formattedMessage);
    }

    public void printOrderedMenu(Map<String, Integer> parsedMenu) {
        String orders = parsedMenu.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + QUANTITY_SUFFIX)
                .collect(Collectors.joining("\n"));
        System.out.println(ORDERED_MENU.getMessage());
        System.out.println(orders);
    }

    public void printTotalOrderAmount(int amount) {
        String formattedAmount = formatMoney(amount);
        System.out.println(AMOUNT_BEFORE_DISCOUNT.getMessage());
        System.out.println(formattedAmount + MONEY_SUFFIX);
    }

    public void printGiftEvent(boolean canGetGift) {
        System.out.println(GIFT_EVENT.getMessage());
        if (canGetGift) {
            System.out.println(CHAMPAIGN_GIFT.getMessage());
            return;
        }
        System.out.println(NO_PROMOTION.getMessage());

    }

    public void printPromotionStatus(Map<String, Integer> promotionStatus) {
        String promotion = determinePromotionStatus(promotionStatus);
        System.out.println(PROMOTION.getMessage());
        System.out.println(promotion);
    }

    public void printPromotionAmount(int promotionAmount){
        String formattedPromotionAmount = determinePromotionAmount(promotionAmount);
        System.out.println(TOTAL_PROMOTION_AMOUNT.getMessage());
        System.out.println(formattedPromotionAmount);
    }

    public void printExpectedPaymentAmount(int amount){
        System.out.println(EXPECTED_AMOUNT.getMessage());
        System.out.println(formatMoney(amount) + MONEY_SUFFIX);
    }

    public void printEventBadge(String badgeName){
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(badgeName);
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
        return NO_PROMOTION.getMessage();
    }

    private String determinePromotionAmount(int promotionAmount){
        if (promotionAmount == 0){
            return (promotionAmount + MONEY_SUFFIX);
        }
        return (MINUS + formatMoney(promotionAmount) + MONEY_SUFFIX);
    }

}
