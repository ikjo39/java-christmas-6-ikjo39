package christmas.constant;

public enum EventDiscountAmount {
    CHRISTMAS_BASIC_DISCOUNT(900),
    CHRISTMAS_DISCOUNT(100),
    WEEK_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000);

    private final int value;

    EventDiscountAmount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
