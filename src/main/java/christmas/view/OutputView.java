package christmas.view;

import static christmas.view.ViewMessage.AMOUNT_BEFORE_DISCOUNT;
import static christmas.view.ViewMessage.CHAMPAIGN_GIFT;
import static christmas.view.ViewMessage.EVENT_BADGE;
import static christmas.view.ViewMessage.EXPECTED_AMOUNT;
import static christmas.view.ViewMessage.GIFT_MENU;
import static christmas.view.ViewMessage.NO_PROMOTION;
import static christmas.view.ViewMessage.ORDERED_MENU;
import static christmas.view.ViewMessage.PROMOTION;
import static christmas.view.ViewMessage.TOTAL_PROMOTION_AMOUNT;

import christmas.model.MenuCount;
import christmas.model.constant.PromotionConstant;
import java.util.EnumMap;
import java.util.Locale;
import java.util.stream.Collectors;

public class OutputView {
    private static final String QUANTITY_SUFFIX = "개";
    private static final String MONEY_SUFFIX = "원";
    private static final String STATUS_DELIMITER = ": ";
    private static final String MINUS = "-";

    public static void printEventGuideMessage(int date) {
        String formattedMessage = String.format(ViewMessage.EVENT.getMessage(), date);
        System.out.println(formattedMessage);
        printEmptyLine();
    }

    public static void printOrderedMenu(MenuCount menuCount) {
        String orders = menuCount.getValue()
                .entrySet().stream()
                .map(entry -> entry.getKey().getName() + " " + entry.getValue() + QUANTITY_SUFFIX)
                .collect(Collectors.joining("\n"));
        System.out.println(ORDERED_MENU.getMessage());
        System.out.println(orders);
        printEmptyLine();
    }

    public static void printTotalOrderAmount(int amount) {
        String formattedAmount = formatMoney(amount);
        System.out.println(AMOUNT_BEFORE_DISCOUNT.getMessage());
        System.out.println(formattedAmount + MONEY_SUFFIX);
        printEmptyLine();
    }

    public static void printGiftEvent(boolean canGetGift) {
        System.out.println(GIFT_MENU.getMessage());
        if (canGetGift) {
            System.out.println(CHAMPAIGN_GIFT.getMessage());
            printEmptyLine();
            return;
        }
        System.out.println(NO_PROMOTION.getMessage());
        printEmptyLine();

    }

    public static void printPromotionStatus(EnumMap<PromotionConstant,Integer> promotionStatus) {
        String promotion = determinePromotionStatus(promotionStatus);
        System.out.println(PROMOTION.getMessage());
        System.out.println(promotion);
        printEmptyLine();
    }

    public static void printPromotionAmount(int promotionAmount){
        String formattedPromotionAmount = determinePromotionAmount(promotionAmount);
        System.out.println(TOTAL_PROMOTION_AMOUNT.getMessage());
        System.out.println(formattedPromotionAmount);
        printEmptyLine();
    }

    public static void printExpectedPaymentAmount(int amount){
        System.out.println(EXPECTED_AMOUNT.getMessage());
        System.out.println(formatMoney(amount) + MONEY_SUFFIX);
        printEmptyLine();
    }

    public static void printEventBadge(String badgeName){
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(badgeName);
    }

    private static String formatMoney(int amount) {
        return String.format(Locale.US, "%,d", amount);
    }

    private static String determinePromotionStatus(EnumMap<PromotionConstant, Integer> promotionStatus) {
        String promotions =  promotionStatus.entrySet().stream()
                    .filter(entry -> entry.getValue() > 0)
                    .map(entry -> entry.getKey().getName() + STATUS_DELIMITER + MINUS
                            + formatMoney(entry.getValue()) + MONEY_SUFFIX)
                    .collect(Collectors.joining("\n"));
        if (promotions.isEmpty()){
            return NO_PROMOTION.getMessage();
        }
        return promotions;
    }

    private static String determinePromotionAmount(int promotionAmount){
        if (promotionAmount == 0){
            return (promotionAmount + MONEY_SUFFIX);
        }
        return (MINUS + formatMoney(promotionAmount) + MONEY_SUFFIX);
    }

    private static void printEmptyLine(){
        System.out.println();
    }

}
