package christmas.constant;

public enum OutputMessage {
    NONE("없음%n"),
    PLANNER_INTRODUCTION("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ENTER_EXPECTED_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ENTER_MENU_AND_COUNT_TO_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ORDERED_MENU("<주문 메뉴>"),
    TOTAL_PRICE("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU("<증정 메뉴>"),
    BENEFITS("<혜택 내역>"),
    TOTAL_BENEFITS_AMOUNT("<총혜택 금액>"),
    AFTER_DISCOUNTED_AMOUNT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>"),

    EVENT_PREVIEW_INTRODUCTION_FORMAT("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n"),
    ORDERED_MENU_FORMAT("%s %d개%n"),
    AMOUNT_FORMAT("%s원%n");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
