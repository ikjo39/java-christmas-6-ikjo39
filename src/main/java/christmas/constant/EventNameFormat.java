package christmas.constant;

public enum EventNameFormat {
    CHRISTMAS_D_DAY_EVENT("크리스마스 디데이 할인: -%s원%n"),
    WEEKDAY_EVENT("평일 할인: -%s원%n"),
    WEEKEND_EVENT("주말 할인: -%s원%n"),
    SPECIAL_EVENT("특별 할인: -%s원%n"),
    GIVEAWAY_EVENT("증정 이벤트: -%s원%n");

    private final String format;

    EventNameFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
