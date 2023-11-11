package christmas.constant;

public enum ExceptionMessage {
    INVALID_VISIT_DAY("유효하지 않은 날짜입니다."),
    INVALID_ORDER("유효하지 않은 주문입니다.");

    private static final String MESSAGE_HEADER = "[ERROR]";
    private static final String MESSAGE_FOOTER = "다시 입력해 주세요.";

    private final String message;

    ExceptionMessage(String message) {
        this.message = String.format("%s %s %s", MESSAGE_HEADER, message, MESSAGE_FOOTER);
    }

    public String getMessage() {
        return message;
    }
}
