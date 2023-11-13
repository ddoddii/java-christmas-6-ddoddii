package christmas.controller;

import static christmas.model.constant.DiscountConstant.GIFT_EVENT_THRESHOLD;

import christmas.model.Date;
import christmas.model.MenuCount;
import christmas.model.constant.PromotionConstant;
import christmas.service.PromotionService;
import christmas.util.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class PromotionController {
    private final PromotionService promotion;

    public PromotionController(PromotionService promotionService) {
        this.promotion = promotionService;
    }

    public void run() {
        InputView.printGreetingMessage();
        Date date = getDate();
        MenuCount menuCount = getMenu();
        displayEventGuideMessage(date);
        displayMenuAndOrderAmount(menuCount);
        displayPromotionResult(menuCount, date);
    }


    private Date getDate() {
        return executeWithExceptionHandle(() -> {
            int inputDate = InputView.readDate();
            return Date.of(inputDate);
        });

    }

    private MenuCount getMenu() {
        return executeWithExceptionHandle(() -> {
            String input = InputView.readMenu();
            Map<String, Integer> parsedMenu = Parser.parseMenuCount(input);
            return new MenuCount(parsedMenu);
        });
    }

    private void displayMenuAndOrderAmount(MenuCount menuCount) {
        displayMenuInfo(menuCount);
        displayTotalOrderAmount(menuCount);
    }

    private void displayPromotionResult(MenuCount menuCount, Date date) {
        displayGiftEvent(menuCount);
        displayPromotionEvent(menuCount, date);
        displayTotalPromotionAmount(menuCount, date);
        displayExpectedPaymentAmount(menuCount, date);
        displayEventBadge(menuCount, date);
    }

    private void displayEventGuideMessage(Date date) {
        OutputView.printEventGuideMessage(date.getValue());
    }


    private void displayMenuInfo(MenuCount menuCount) {
        OutputView.printOrderedMenu(menuCount);
    }

    private void displayTotalOrderAmount(MenuCount menuCount) {
        OutputView.printTotalOrderAmount(menuCount.calculateTotalAmount());
    }

    private void displayGiftEvent(MenuCount menuCount) {
        boolean canGiveGift = menuCount.calculateTotalAmount() >= GIFT_EVENT_THRESHOLD.getAmount();
        OutputView.printGiftEvent(canGiveGift);
    }

    private void displayPromotionEvent(MenuCount menuCount, Date date) {
        EnumMap<PromotionConstant, Integer> promotionStatus = promotion.calculatePromotionStatus(menuCount, date);
        OutputView.printPromotionStatus(promotionStatus);
    }

    private void displayTotalPromotionAmount(MenuCount menuCount, Date date) {
        int totalPromotionAmount = promotion.calculatePromotionAmount(menuCount, date);
        OutputView.printPromotionAmount(totalPromotionAmount);
    }

    private void displayExpectedPaymentAmount(MenuCount menuCount, Date date) {
        int expectedPayment =
                menuCount.calculateTotalAmount() - promotion.calaulateTotalDiscountAmount(menuCount, date);
        OutputView.printExpectedPaymentAmount(expectedPayment);
    }

    private void displayEventBadge(MenuCount menuCount, Date date) {
        String badgeName = promotion.calculateBadgeStatus(menuCount, date);
        OutputView.printEventBadge(badgeName);
    }

    private static <T> T executeWithExceptionHandle(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
