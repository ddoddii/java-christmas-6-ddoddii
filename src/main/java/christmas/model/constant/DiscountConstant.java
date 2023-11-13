package christmas.model.constant;

public enum DiscountConstant {
    CHRISTMAS_START_DISCOUNT(1000),
    DISCOUNT_INCREMENT(100),
    MIN_DISCOUNT_SERVICE(10000),
    FIXED_DISCOUNT(2023),
    SPECIAL_DAY_DISCOUNT(1000),
    GIFT_EVENT_THRESHOLD(120000),
    NO_DISCOUNT(0);

    private int amount;

    DiscountConstant(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
