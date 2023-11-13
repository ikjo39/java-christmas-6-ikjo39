package christmas.constant;

public enum DiscountEvent {
    CHRISTMAS_D_DAY_EVENT("크리스마스 디데이 할인: -%s원%n", 100),
    WEEKDAY_EVENT("평일 할인: -%s원%n", 2023),
    WEEKEND_EVENT("주말 할인: -%s원%n", 2023),
    SPECIAL_EVENT("특별 할인: -%s원%n", 2023),
    GIVEAWAY_EVENT("증정 이벤트: -%s원%n", 0);

    private final String format;
    private final int discountAmount;

    DiscountEvent(String format, int discountAmount) {
        this.format = format;
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getFormat() {
        return format;
    }
}
