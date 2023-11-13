package christmas.model;

public class TotalPrice {
    private static final int MINIMUM_PRICE_TO_EVENT_START = 10_000;
    private static final int MINIMUM_PRICE_TO_GET_GIVEAWAY = 120_000;

    private final int totalPrice;

    public TotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isEventEnabled() {
        return totalPrice >= MINIMUM_PRICE_TO_EVENT_START;
    }

    public boolean isSatisfiedGiveaway() {
        return totalPrice >= MINIMUM_PRICE_TO_GET_GIVEAWAY;
    }

    public int calculateAfterDiscountedAmount(Benefits benefits) {
        return totalPrice - benefits.getTotalDiscounts();
    }
}
