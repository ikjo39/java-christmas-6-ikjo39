package christmas.constant;

public enum OutputMessage {
    PLANNER_INTRODUCTION("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ENTER_EXPECTED_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ENTER_MENU_AND_COUNT_TO_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ORDERED_MENU("<주문 메뉴>"),

    EVENT_PREVIEW_INTRODUCTION_FORMAT("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n"),
    ORDERED_MENU_FORMAT("%s %d%n");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
